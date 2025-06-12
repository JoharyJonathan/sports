import redis
from .config import REDIS_CONFIG

r = redis.Redis(**REDIS_CONFIG)