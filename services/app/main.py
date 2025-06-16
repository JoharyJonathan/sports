from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from .config import ALLOWED_ORIGINS
from .routes import history, products, recommendations
from .utils.recommender import initialize_recommender_engine

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=ALLOWED_ORIGINS,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(history.router)
app.include_router(products.router)
app.include_router(recommendations.router)

@app.on_event("startup")
async def startup_event():
    await initialize_recommender_engine()
    print('Demarrage de l\'app')