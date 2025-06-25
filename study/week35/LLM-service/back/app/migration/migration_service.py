import csv

from app.core.qdrant_client import create_collection
from app.embedding.embedder import get_embedding
from app.schemas.whisky_schema import WhiskyData
from app.translation.translator import translate_en_to_ko
from config import settings

client = create_collection(settings.collection_en, 768)

async def migrate_data(file_path: str):
    whisky_list = []
    with open(file_path, 'r', encoding='utf-8') as file:
        reader = csv.DictReader(file)
        for row in reader:
            row[''] = int(row[''])  # id 필드
            row['review.point'] = float(row['review.point'])
            translate_ko = await translate_en_to_ko(row['description'])
            row['description_ko'] = translate_ko
            whisky = WhiskyData(**row)
            whisky_list.append(whisky)

            if len(whisky_list) == 500:
                _bulk_upsert(whisky_list)
                whisky_list = []

        if whisky_list:
            _bulk_upsert(whisky_list)


def _bulk_upsert(whisky_list):
    points = []
    for whisky in whisky_list:
        vector = get_embedding(whisky.description)
        points.append({
            'id': whisky.id,
            'vector': vector,
            'payload': whisky.dict(by_alias=True)
        })

    client.upsert(
        collection_name=settings.collection_en,
        points=points
    )
