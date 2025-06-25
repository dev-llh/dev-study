from sentence_transformers import SentenceTransformer
from config import settings

model = SentenceTransformer(settings.embedding_model, device="cuda")

def get_embedding(text: str) -> list[float]:
    return model.encode(text).tolist()