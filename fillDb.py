import requests
from faker import Faker
import random
from datetime import datetime, timedelta

# Initialisation de Faker
fake = Faker()
host = "http://localhost:8081"

# Génération et envoi de requêtes POST pour les utilisateurs
for _ in range(10):
    data = {
        "email": fake.email(),
        "password": fake.password(),
        "city": fake.city(),
        "region": fake.state(),
        "age": fake.random_int(min=18, max=65),
        "interests": ", ".join(fake.words(nb=3))
    }
    print("input")
    print(data)

    response = requests.post(host + "/users", json=data)
    if response.status_code == 200:
        print(f"Requête POST réussie avec l'ID : {response.json()['id']}")
        print(response.json())
        print()

# Génération et envoi de requêtes POST pour les partys
for _ in range(10):
    data = {
        "name": fake.catch_phrase(),
        "description": fake.text(),
        "location": fake.address(),
        "date": (datetime.now() + timedelta(days=random.randint(1, 30))).isoformat(),
        "maxParticipants": random.randint(5, 20),
        "userOwner": {
            "email": fake.email(),
            "password": fake.password(),
            "city": fake.city(),
            "region": fake.state(),
            "age": fake.random_int(min=18, max=65),
            "interests": ", ".join(fake.words(nb=3))
        },
        "participants": [
            {
                "email": fake.email(),
                "password": fake.password(),
                "city": fake.city(),
                "region": fake.state(),
                "age": fake.random_int(min=18, max=65),
                "interests": ", ".join(fake.words(nb=3))
            } for _ in range(random.randint(1, 5))
        ],
        "paid": random.choice([True, False])
    }
    print("input")
    print(data)

    response = requests.post(host + "/partys", json=data)
    if response.status_code == 200:
        print(f"Requête POST réussie avec l'ID : {response.json()['id']}")
        print(response.json())
        print()

# Génération et envoi de requêtes POST pour les reviews
for _ in range(10):
    data = {
        "rating": random.randint(1, 5),
        "comment": fake.sentence(),
        "userOwner": {
            "email": fake.email(),
            "password": fake.password(),
            "city": fake.city(),
            "region": fake.state(),
            "age": fake.random_int(min=18, max=65),
            "interests": ", ".join(fake.words(nb=3))
        }
    }
    print("input")
    print(data)

    response = requests.post(host + "/reviews", json=data)
    if response.status_code == 200:
        print(f"Requête POST réussie avec l'ID : {response.json()['id']}")
        print(response.json())
        print()
