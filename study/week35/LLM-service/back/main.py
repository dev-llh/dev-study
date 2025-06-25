from fastapi import FastAPI
from starlette.responses import JSONResponse

from app.api import migration_router, recommendation_router

app = FastAPI()


app.include_router(migration_router.router, tags=["Migration"])
app.include_router(recommendation_router.router, tags=["Recommendation"])

@app.get("/health")
async def health():
    return JSONResponse(status_code=200, content={"message": "OK"})

