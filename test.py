import pytest
import requests
from faker import Faker
fake = Faker()
BASE_URL = "http://localhost:8081"
created_user_id=0
def test_get_all_users():
    response = requests.get(f"{BASE_URL}/users")
    assert response.status_code == 200
    data = response.json()
    assert isinstance(data, list)

def test_create_user():
    user_data = {
        "email": fake.email(),
        "password": fake.password(),
        "city": fake.city(),
        "region": fake.state(),
        "age": fake.random_int(min=18, max=65),
        "interests": ", ".join(fake.words(nb=3))
    }
    response = requests.post(f"{BASE_URL}/users", json=user_data)
    assert response.status_code == 200
    pytest.created_user_id=response.json()["id"]

@pytest.mark.dependency(depends=["test_create_user"])
def test_get_user_by_id():
    response = requests.get(f"{BASE_URL}/users/{pytest.created_user_id}")
    assert response.status_code == 200
    user = response.json()
    assert user["id"] == pytest.created_user_id

# def test_update_user():
#     created_user_id = test_create_user()
#     updated_data = {
#         "email": "updated@example.com",
#         "city": "San Francisco",
#         "age": 35
#     }
#     response = requests.put(f"{BASE_URL}/users/{created_user_id}", json=updated_data)
#     assert response.status_code == 200
#     updated_user = response.json()
#     assert updated_user["email"] == updated_data["email"]
#     assert updated_user["city"] == updated_data["city"]
#     assert updated_user["age"] == updated_data["age"]

# def test_delete_user():
#     created_user_id = test_create_user()
#     response = requests.delete(f"{BASE_URL}/users/{created_user_id}")
#     assert response.status_code == 200
