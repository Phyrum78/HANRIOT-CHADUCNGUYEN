import requests
from faker import Faker

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


    # Vérification de la réponse
    if response.status_code == 200:
        print(f"Requête POST réussie avec l'ID : {response.json()['id']}")
        print(response.json())
        print()

