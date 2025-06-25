from fastapi import APIRouter
from starlette.responses import JSONResponse

from app.migration.migration_service import migrate_data
from config import settings

router = APIRouter(prefix="/migrate", tags=["Migration"])

@router.post("")
async def migrate():
    await migrate_data(settings.csv_path)
    return JSONResponse(status_code=200, content={"message": "Migration completed successfully."})
