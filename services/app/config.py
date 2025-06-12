from fastapi.middleware.cors import CORSMiddleware

ALLOWED_ORIGINS = [
    "http://localhost:5173",
    "http://localhost:8080",
]

REDIS_CONFIG = {
    "host": "localhost",
    "port": 6379,
    "decode_responses": True
}