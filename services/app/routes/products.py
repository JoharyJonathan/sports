from fastapi import APIRouter
from ..db import db
from ..models import Product

router = APIRouter()

@router.get("/products")
async def get_all_products():
    products_cursor = db["products"].find()
    products = []
    async for product in products_cursor:
        products.append(Product(**product))
    return products