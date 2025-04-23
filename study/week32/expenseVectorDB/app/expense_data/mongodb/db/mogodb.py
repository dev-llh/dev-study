from motor.motor_asyncio import AsyncIOMotorClient

from app.core.config.config import settings

mongo_client = AsyncIOMotorClient(settings.mongodb_uri)
mongo_db = mongo_client[settings.mongodb_db]
expense_collection = mongo_db.get_collection("expense")