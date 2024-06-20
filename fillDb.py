import requests
from faker import Faker
import random
from datetime import datetime, timedelta

# Initialisation de Faker
fake = Faker()
host="http://localhost:8081"
# Boucle pour générer 10 requêtes POST
for _ in range(10):
    # Génération des données fictives
    data = {
        "email": fake.email(),
        "password": fake.password(),
        "city": fake.city(),
        "region": fake.state(),
        "age": fake.random_int(min=18, max=65),
        "interests": ", ".join(fake.words(nb=3))
    }
    print ("input")
    print( data)

    # Envoi de la requête POST
    response = requests.post(host+"/users", json=data)

# Loop to generate 10 POST requests
for _ in range(10):
    # Generate fake data
    data = {
        "name": fake.catch_phrase(),
        "description": fake.text(),
        "location": fake.address(),
        "date": (datetime.now() + timedelta(days=random.randint(1, 365))).isoformat(),
        "maxParticipants": random.randint(5, 500),
        "isPaid": random.choice([True, False])
    }
    print("input")
    print(data)

    # Send the POST request
    response = requests.post(host + "/partys", json=data)

    # Check the response
    if response.status_code == 200:
        print(f"POST request successful with ID: {response.json()['id']}")
        print(response.json())
        print()

    # Vérification de la réponse
    if response.status_code == 200:
        print(f"Requête POST réussie avec l'ID : {response.json()['id']}")
        print(response.json())
        print()


# Loop to generate 10 POST requests
for _ in range(10):
    # Generate fake data
    data = {
        "userId": random.randint(1, 100),  # Assuming you have users with IDs 1-100
        "reviewerId": random.randint(1, 100),  # Assuming you have users with IDs 1-100
        "rating": random.randint(1, 5),  # Assuming rating is from 1 to 5
        "comment": fake.text(max_nb_chars=200)  # A random text of up to 200 characters
    }
    print("input")
    print(data)

    # Send the POST request
    response = requests.post(host + "/reviews", json=data)

    # Check the response
    if response.status_code == 200:
        print(f"POST request successful with ID: {response.json()['id']}")
        print(response.json())
        print()
