import asyncio
from typing import List
from qdrant_client.http.models import PointStruct

from app.core.config.config import settings
from app.core.sentence_model.vectorizer import get_expense_embedding
from app.expense_data.qdrantdb.db.qdrant import qdrant_client
from app.expense_data.qdrantdb.dto.qdrant_dto import ExpenseVectorRequest, ExpenseVectorSearchRequest
from app.expense_data.qdrantdb.model.expense_vecotr_entity import ExpenseVector

async def upsert_expenses_vector(requests: List["ExpenseVectorRequest"]):
    # 병렬로 임베딩 벡터 생성 (실패 허용)
    embedding_tasks = [get_expense_embedding(req.vectorize_info) for req in requests]
    results = await asyncio.gather(*embedding_tasks, return_exceptions=True)

    points = []
    for req, result in zip(requests, results):
        if isinstance(result, Exception):
            print(f"[ERROR] Failed to embed ID {req.id}: {result}")
            continue

        try:
            payload = ExpenseVector(id=req.id, embedding=result, metadata=req.dict())
            point = PointStruct(
                id=payload.id,
                vector=payload.embedding,
                payload=payload.metadata
            )
            points.append(point)
        except Exception as e:
            print(f"[ERROR] Failed to build point for ID {req.id}: {e}")
            continue

    if not points:
        print("[INFO] No points to upsert. Skipping Qdrant call.")
        return

    # Qdrant 업서트도 실패해도 로깅하고 계속 진행
    try:
        qdrant_client.upsert(
            collection_name=settings.qdrant_collection,
            points=points,
        )
    except Exception as e:
        print(f"[ERROR] Qdrant upsert failed: {e}")

async def search_expense_vector(request: ExpenseVectorSearchRequest):
    vector = await get_expense_embedding(request.expense_text)

    search_result = qdrant_client.search(
        collection_name=settings.qdrant_collection,
        query_vector=vector,
        limit=request.limit,
        score_threshold=0.5
    )

    return search_result