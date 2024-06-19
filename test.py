import pytest
import requests

BASE_URL = "http://localhost:8081"

def test_get_all_users():
    response = requests.get(f"{BASE_URL}/users")
    assert response.status_code == 200
    data = response.json()
    assert isinstance(data, list)

def test_create_user():
    user_data = {
        "email": "test@example.com",
        "password": "password123",
        "city": "New York",
        "region": "NY",
        "age": 30,
        "interests": "coding, reading"
    }
    response = requests.post(f"{BASE_URL}/users", json=user_data)
    assert response.status_code == 200
    created_user = response.json()
    assert created_user["email"] == user_data["email"]
    assert created_user["city"] == user_data["city"]
    return created_user["id"]

def test_get_user_by_id():
    created_user_id = test_create_user()
    response = requests.get(f"{BASE_URL}/users/{created_user_id}")
    assert response.status_code == 200
    user = response.json()
    assert user["id"] == created_user_id

def test_update_user():
    created_user_id = test_create_user()
    updated_data = {
        "email": "updated@example.com",
        "city": "San Francisco",
        "age": 35
    }
    response = requests.put(f"{BASE_URL}/users/{created_user_id}", json=updated_data)
    assert response.status_code == 200
    updated_user = response.json()
    assert updated_user["email"] == updated_data["email"]
    assert updated_user["city"] == updated_data["city"]
    assert updated_user["age"] == updated_data["age"]

def test_delete_user():
    created_user_id = test_create_user()
    response = requests.delete(f"{BASE_URL}/users/{created_user_id}")
    assert response.status_code == 200
