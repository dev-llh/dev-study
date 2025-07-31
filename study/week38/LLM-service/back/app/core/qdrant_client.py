from qdrant_client import QdrantClient
from qdrant_client.http.models import VectorParams, Distance
from config import settings

client = QdrantClient(
    host=settings.qdrant_host,
    port=settings.qdrant_port,
)

def create_collection(collection_name: str, vector_size: int = 768):
    if not client.collection_exists(collection_name):
        client.create_collection(
            collection_name=collection_name,
            vectors_config=VectorParams(
                size=vector_size,
                distance=Distance.COSINE
            )
        )

    return client

