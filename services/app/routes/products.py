from fastapi import APIRouter
from fastapi.responses import StreamingResponse
from ..db import db
from ..models import Product
import csv
import io
import os
import pandas as pd

router = APIRouter()

@router.get("/products")
async def get_all_products():
    products_cursor = db["products"].find()
    products = []
    async for product in products_cursor:
        products.append(Product(**product))
    return products

@router.get("/products/export/csv")
async def export_products_csv():
    products_cursor = db["products"].find()
    products = []
    async for product in products_cursor:
        products.append(Product(**product).dict(by_alias=True))

    buffer = io.StringIO()
    writer = csv.DictWriter(buffer, fieldnames=["_id", "name", "category", "description", "stock_quantity", "price", "image_Url"])
    writer.writeheader()
    for product in products:
        writer.writerow(product)

    buffer.seek(0)
    return StreamingResponse(buffer, media_type="text/csv", headers={"Content-Disposition": "attachment; filename=products.csv"})

EXPORT_PATH = os.path.join(os.path.dirname(__file__), "../private_exports/products.csv")

@router.post("/products/export/local")
async def export_products_to_local_csv():
    products_cursor = db["products"].find()
    products = []
    async for product in products_cursor:
        products.append(Product(**product).dict(by_alias=True))

    # Crée le dossier s’il n’existe pas
    os.makedirs(os.path.dirname(EXPORT_PATH), exist_ok=True)

    # Écriture dans le fichier CSV
    with open(EXPORT_PATH, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=["_id", "name", "category", "description", "stock_quantity", "price", "image_Url"])
        writer.writeheader()
        for product in products:
            writer.writerow(product)

    return {"message": f"Fichier exporté avec succès à {EXPORT_PATH}"}

@router.get("/categories")
async def get_categories():
    df = pd.read_csv("app/private_exports/products.csv")
    df["category"] = df["category"].fillna("")
    unique_categories = df["category"].unique().tolist()
    
    return {"categories": unique_categories}