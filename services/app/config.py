from fastapi.middleware.cors import CORSMiddleware

ALLOWED_ORIGINS = [
    "http://localhost:5173",  # ton app Vue.js
    # ajoute d'autres domaines en prod
]

REDIS_CONFIG = {
    "host": "localhost",
    "port": 6379,
    "decode_responses": True
}