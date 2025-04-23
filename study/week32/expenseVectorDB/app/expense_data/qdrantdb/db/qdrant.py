from qdrant_client import QdrantClient
from qdrant_client.http.models import VectorParams, Distance

from app.core.config.config import settings

qdrant_client = QdrantClient(host=settings.qdrant_host, port=settings.qdrant_port)

vector_size = 768

collections = qdrant_client.get_collections()

existing_collections = [col.name for col in collections.collections]

print(existing_collections)

if settings.qdrant_collection not in existing_collections:
    qdrant_client.create_collection(
        collection_name=settings.qdrant_collection,
        vectors_config=VectorParams(
            size=vector_size,
            distance=Distance.COSINE,
        )
    )