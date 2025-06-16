from fastapi import APIRouter, HTTPException
from app.utils.recommender import recommend_products

router = APIRouter()

@router.get("/recommend/{product_name}")
async def get_recommendations(product_name: str, top_n: int = 4):
    recommendations = recommend_products(product_name, top_n)
    if not recommendations:
        raise HTTPException(status_code=404, detail="Aucun produit similaire trouvé")
    return recommendations