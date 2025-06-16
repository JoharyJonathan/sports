import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import os
import csv
from app.db import db
from app.models import Product 

EXPORT_PATH = os.path.join(os.path.dirname(__file__), "../private_exports/products.csv")

df_products = None
cosine_sim = None
vectorizer = None 

async def export_products_to_local_csv_for_recommender():
    """
    Exporte les données des produits de MongoDB vers un fichier CSV local.
    Cette fonction est conçue pour être appelée au démarrage de l'application.
    """
    products_cursor = db["products"].find()
    products_data = []
    async for product in products_cursor:
        products_data.append(Product(**product).dict(by_alias=True))

    os.makedirs(os.path.dirname(EXPORT_PATH), exist_ok=True)

    fieldnames = ["_id", "name", "category", "description", "stock_quantity", "price", "image_url"]
    
    with open(EXPORT_PATH, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()
        for product in products_data:
            writer.writerow(product)
    print(f"Produits exportés avec succès vers {EXPORT_PATH}")

def load_products_data_from_csv(csv_path: str = EXPORT_PATH) -> pd.DataFrame:
    if not os.path.exists(csv_path) or os.stat(csv_path).st_size == 0:
        print(f"Avertissement: Le fichier CSV '{csv_path}' est vide ou n'existe pas. Retourne un DataFrame vide.")
        return pd.DataFrame(columns=["_id", "name", "category", "description", "stock_quantity", "price", "image_url", "features"])

    df = pd.read_csv(csv_path)
    df = df.fillna("")
    df["features"] = df["name"] + " " + df["category"] + " " + df["description"]
    return df

def build_similarity_matrix_from_df(df: pd.DataFrame):
    global vectorizer
    vectorizer = TfidfVectorizer(stop_words="english")
    
    if df.empty or df["features"].str.strip().eq("").all():
        raise ValueError("Impossible de construire le vocabulaire: la colonne 'features' est vide ou ne contient que des mots vides après traitement.")
        
    tfidf_matrix = vectorizer.fit_transform(df["features"])
    cosine_sim_matrix = cosine_similarity(tfidf_matrix)
    return cosine_sim_matrix

async def initialize_recommender_engine():
    global df_products, cosine_sim
    print("Initialisation du moteur de recommandation...")
    
    await export_products_to_local_csv_for_recommender() 
    
    df_products = load_products_data_from_csv()
    
    if df_products.empty:
        print("Aucun produit dans le CSV, le moteur de recommandation ne pourra pas fournir de recommandations.")
        cosine_sim = None
    else:
        try:
            cosine_sim = build_similarity_matrix_from_df(df_products)
            print("Matrice de similarité du moteur de recommandation construite avec succès.")
        except ValueError as e:
            print(f"Échec de la construction de la matrice de similarité: {e}. Le moteur de recommandation pourrait ne pas fonctionner.")
            cosine_sim = None

def recommend_products(product_name: str, top_n: int = 3):
    global df_products, cosine_sim, vectorizer

    if df_products is None or cosine_sim is None or df_products.empty:
        print("Moteur de recommandation non initialisé ou aucune donnée disponible. Retourne une liste vide.")
        return []

    indices = pd.Series(df_products.index, index=df_products["name"])

    if product_name not in indices:
        print(f"Produit '{product_name}' non trouvé dans l'index.")
        return []

    idx = indices[product_name]
    if isinstance(idx, pd.Series):
        idx = idx.iloc[0] 

    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1: top_n + 1]
    product_indices = [i[0] for i in sim_scores]

    recommendations = df_products.iloc[product_indices][["_id", "name", "category", "description", "price", "image_url"]]
    return recommendations.to_dict(orient="records")

def fallback_recommendations(method: str = "random", top_n: int = 4):
    global df_products
    
    if df_products is None or df_products.empty:
        print("Aucune donnees de produit disponble pour le fallback")
        return []
    
    if method == "popular":
        fallback = df_products.sort_values("stock_quantity", ascending=False).head(top_n)
    else:
        fallback = df_products.sample(n=min(top_n, len(df_products)))
        
    return fallback[["_id", "name", "category", "description", "price", "image_url"]].to_dict(orient="records")