from fastapi import APIRouter
from ..redis_client import r

router = APIRouter()

@router.get("/history")
def get_global_history():
    keys = r.keys("product:*")
    products = []
    for key in keys:
        product_data = r.get(key)
        if product_data:
            products.append({"key": key.decode("utf-8") if isinstance(key, bytes) else key,
                             "data": product_data.decode("utf-8") if isinstance(product_data, bytes) else product_data})
    return {"global_history": products}