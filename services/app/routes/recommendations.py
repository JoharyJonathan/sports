from fastapi import APIRouter, HTTPException, Query
from app.utils.recommender import recommend_products, fallback_recommendations

router = APIRouter()

@router.get("/recommend/fallback")
async def get_fallback_recommendations(
    method: str = Query(default="random", enum=["random", "popular"]), top_n: int = 4
):
    recommendations = fallback_recommendations(method=method, top_n=top_n)
    if not recommendations:
        raise HTTPException(status_code=404, detail="Aucune recommendation possible pour le fallback")
    
    return recommendations

@router.get("/recommend/{product_name}")
async def get_recommendations(product_name: str, top_n: int = 4):
    recommendations = recommend_products(product_name, top_n)
    if not recommendations:
        raise HTTPException(status_code=404, detail="Aucun produit similaire trouvé")
    return recommendations