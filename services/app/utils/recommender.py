import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# Fonction pour charger et préparer les données du CSV
def load_products(csv_path: str = "app/private_exports/products.csv") -> pd.DataFrame:
    df = pd.read_csv(csv_path)
    df = df.fillna("")
    # Combiner les colonnes textuelles en une seule "feature"
    df["features"] = df["name"] + " " + df["category"] + " " + df["description"]
    return df

# Construire la matrice de similarité à l'aide de TF-IDF
def build_similarity_matrix(df: pd.DataFrame):
    vectorizer = TfidfVectorizer(stop_words="english")
    tfidf_matrix = vectorizer.fit_transform(df["features"])
    cosine_sim = cosine_similarity(tfidf_matrix)
    return cosine_sim

# Charger les données et construire la matrice de similarité une seule fois au démarrage
df_products = load_products()
cosine_sim = build_similarity_matrix(df_products)

# Fonction de recommandation qui retourne les produits similaires
def recommend_products(product_name: str, top_n: int = 3):
    indices = pd.Series(df_products.index, index=df_products["name"])

    if product_name not in indices:
        return []

    idx = indices[product_name]
    if isinstance(idx, pd.Series):
        idx = idx.iloc[0] 

    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1: top_n + 1]
    product_indices = [i[0] for i in sim_scores]

    recommendations = df_products.iloc[product_indices][["_id", "name", "category", "description", "price"]]
    return recommendations.to_dict(orient="records")

