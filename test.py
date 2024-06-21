import pytest
import requests
from faker import Faker
from datetime import datetime, timedelta

BASE_URL = "http://localhost:8081"  # Replace with your actual API base URL
fake = Faker()

@pytest.fixture(scope="module")
def api_client():
    return requests.Session()

def generate_user_data():
    return {
        "email": fake.email(),
        "password": fake.password(),
        "city": fake.city(),
        "region": fake.state(),
        "age": fake.random_int(min=18, max=80),
        "interests": fake.sentence()
    }

def generate_review_data(user_id=None):
    user = generate_user_data()
    user["id"] = user_id
    return {
        "rating": fake.random_int(min=1, max=5),
        "comment": fake.paragraph(),
        "userOwner": user
    }

def generate_party_data(user_id=None):
    user = generate_user_data()
    user["id"] = user_id
    return {
        "name": fake.company(),
        "description": fake.text(),
        "location": fake.address(),
        "date": (datetime.now() + timedelta(days=fake.random_int(min=1, max=30))).isoformat(),
        "maxParticipants": fake.random_int(min=5, max=100),
        "userOwner": user,
        "participants": [],
        "paid": fake.boolean()
    }
def assertUser(userInput, userOutput):
    assert userOutput["email"] == userInput["email"]
    assert userOutput["city"] == userInput["city"]
    assert userOutput["region"] == userInput["region"]
    assert userOutput["age"] == userInput["age"]
    assert userOutput["interests"] == userInput["interests"]
    assert userOutput["interests"] == userInput["interests"]

def assertReview(reviewInput, reviewOutput):
    assert reviewOutput["rating"] == reviewInput["rating"]
    assert reviewOutput["comment"] == reviewInput["comment"]
    assert "id" in reviewOutput  # Ensure an ID was assigned

def assertParty(partyInput, partyOutput):
    assert partyOutput["name"] == partyInput["name"]
    assert partyOutput["description"] == partyInput["description"]
    assert partyOutput["location"] == partyInput["location"]
    assert partyOutput["maxParticipants"] == partyInput["maxParticipants"]
    assert partyOutput["paid"] == partyInput["paid"]
    assert len(partyOutput["participants"]) == len(partyInput["participants"])
    for input_participant, output_participant in zip(partyInput["participants"], partyOutput["participants"]):
        assertUser(input_participant, output_participant)
    assert "id" in partyOutput  # Ensure an ID was assigned

unassignedId = 1000000000

@pytest.mark.parametrize("endpoint", ["/users", 
                                    "/reviews",
                                       "/partys"
                                      ])
def test_crud_operations(api_client, endpoint):
    # Get one by non-existent ID
    response = api_client.get(f"{BASE_URL}{endpoint}/{unassignedId}")
    assert response.status_code == 404

    # Insert one
    if endpoint == "/users":
        data = generate_user_data()
    elif endpoint == "/reviews":
        # Assuming a user exists with ID 1 for the review
        data = generate_review_data()
    else:  # parties
        # Assuming a user exists with ID 1 for the party
        data = generate_party_data()

    response = api_client.post(f"{BASE_URL}{endpoint}", json=data)
    assert response.status_code == 200
    created_id = response.json()["id"]
    created_content = response.json()

    # Get one by ID
    response = api_client.get(f"{BASE_URL}{endpoint}/{created_id}")
    assert response.status_code == 200
    assert response.json() == created_content

    # Find all
    response = api_client.get(f"{BASE_URL}{endpoint}?amount=1000000000")
    assert response.status_code == 200
    assert response.json()[-1]["id"] == created_id

    # Update one by ID
    if endpoint == "/users":
        update_data = generate_user_data()
        update_data["id"] = created_id
    elif endpoint == "/reviews":
        update_data = generate_review_data()
        update_data["id"] = created_id
    else:  # parties
        update_data = generate_party_data()
        update_data["id"] = created_id    

    response = api_client.put(f"{BASE_URL}{endpoint}", json=update_data)
    assert response.status_code == 200
    update_data["id"]=unassignedId
    response = api_client.put(f"{BASE_URL}{endpoint}", json=update_data)
    assert response.status_code == 404
    

    # Get one by ID and check update
    response = api_client.get(f"{BASE_URL}{endpoint}/{created_id}")
    assert response.status_code == 200
    if endpoint == "/users":
        assertUser(update_data,response.json())
    elif endpoint == "/reviews":
       assertReview(update_data,response.json())
    else:  # parties
        assertParty(update_data,response.json())

    # Delete one by ID
    response = api_client.delete(f"{BASE_URL}{endpoint}/{created_id}")
    assert response.status_code == 200

    # Attempt to delete non-existent ID
    response = api_client.delete(f"{BASE_URL}{endpoint}/{unassignedId}")
    assert response.status_code == 404

if __name__ == "__main__":
    pytest.main()
