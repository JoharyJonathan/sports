import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import os
import csv
from app.db import db
from app.models import Product 

# Chemin où le CSV sera exporté
EXPORT_PATH = os.path.join(os.path.dirname(__file__), "../private_exports/products.csv")

# Variables globales pour le moteur de recommandation (initialisées plus tard)
df_products = None
cosine_sim = None
vectorizer = None 

# Fonction pour exporter les produits de la base de données vers le CSV local
# C'est une version sync/async adaptée pour être appelée au démarrage de l'application
async def export_products_to_local_csv_for_recommender():
    """
    Exporte les données des produits de MongoDB vers un fichier CSV local.
    Cette fonction est conçue pour être appelée au démarrage de l'application.
    """
    products_cursor = db["products"].find()
    products_data = []
    async for product in products_cursor:
        products_data.append(Product(**product).dict(by_alias=True))

    # Crée le dossier s'il n'existe pas
    os.makedirs(os.path.dirname(EXPORT_PATH), exist_ok=True)

    # Écriture dans le fichier CSV
    # Assurez-vous que les noms de champs correspondent à votre modèle Product
    fieldnames = ["_id", "name", "category", "description", "stock_quantity", "price", "image_url"]
    
    with open(EXPORT_PATH, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()
        for product in products_data:
            writer.writerow(product)
    print(f"Produits exportés avec succès vers {EXPORT_PATH}")


# Fonction pour charger et préparer les données du CSV
def load_products_data_from_csv(csv_path: str = EXPORT_PATH) -> pd.DataFrame:
    # Vérifie si le fichier existe et n'est pas vide
    if not os.path.exists(csv_path) or os.stat(csv_path).st_size == 0:
        print(f"Avertissement: Le fichier CSV '{csv_path}' est vide ou n'existe pas. Retourne un DataFrame vide.")
        # Retourne un DataFrame vide avec les colonnes attendues pour éviter des erreurs plus tard
        return pd.DataFrame(columns=["_id", "name", "category", "description", "stock_quantity", "price", "image_url", "features"])

    df = pd.read_csv(csv_path)
    df = df.fillna("") # Remplace les valeurs manquantes par des chaînes vides
    # Combine les colonnes textuelles en une seule colonne "features"
    df["features"] = df["name"] + " " + df["category"] + " " + df["description"]
    return df

# Fonction pour construire la matrice de similarité TF-IDF
def build_similarity_matrix_from_df(df: pd.DataFrame):
    global vectorizer # On accède à la variable globale vectorizer
    vectorizer = TfidfVectorizer(stop_words="english") # Utilisez "english" ou changez si vos textes sont en français
    
    # Vérifie si la colonne "features" est vide ou ne contient que des "stop words"
    if df.empty or df["features"].str.strip().eq("").all():
        raise ValueError("Impossible de construire le vocabulaire: la colonne 'features' est vide ou ne contient que des mots vides après traitement.")
        
    tfidf_matrix = vectorizer.fit_transform(df["features"])
    cosine_sim_matrix = cosine_similarity(tfidf_matrix)
    return cosine_sim_matrix

# Fonction principale pour initialiser le moteur de recommandation
async def initialize_recommender_engine():
    global df_products, cosine_sim
    print("Initialisation du moteur de recommandation...")
    
    # Étape 1: S'assurer que le fichier CSV est rempli avec les données les plus récentes
    await export_products_to_local_csv_for_recommender() 
    
    # Étape 2: Charger les produits à partir du CSV (maintenant rempli)
    df_products = load_products_data_from_csv()
    
    # Étape 3: Construire la matrice de similarité si des produits ont été chargés
    if df_products.empty:
        print("Aucun produit dans le CSV, le moteur de recommandation ne pourra pas fournir de recommandations.")
        cosine_sim = None # Ou une matrice vide pour gérer ce cas
    else:
        try:
            cosine_sim = build_similarity_matrix_from_df(df_products)
            print("Matrice de similarité du moteur de recommandation construite avec succès.")
        except ValueError as e:
            # Si la construction échoue (ex: encore des textes vides ou seulement des stop words)
            print(f"Échec de la construction de la matrice de similarité: {e}. Le moteur de recommandation pourrait ne pas fonctionner.")
            cosine_sim = None # S'assurer que la variable est à None en cas d'échec

# Fonction de recommandation de produits (elle utilisera les variables globales)
def recommend_products(product_name: str, top_n: int = 3):
    global df_products, cosine_sim, vectorizer

    # Vérifie si le moteur a été initialisé et s'il y a des données
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
    sim_scores = sim_scores[1: top_n + 1] # On exclut le produit lui-même
    product_indices = [i[0] for i in sim_scores]

    recommendations = df_products.iloc[product_indices][["_id", "name", "category", "description", "price", "image_url"]]
    return recommendations.to_dict(orient="records")