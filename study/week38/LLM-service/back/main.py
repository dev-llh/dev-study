from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from starlette.responses import JSONResponse

from app.api import migration_router, recommendation_router

app = FastAPI()

origins = [
    "http://localhost:3000"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,          # 허용할 origin 리스트
    allow_credentials=True,
    allow_methods=["*"],            # 모든 HTTP 메서드 허용
    allow_headers=["*"],            # 모든 헤더 허용
)


app.include_router(migration_router.router, tags=["Migration"])
app.include_router(recommendation_router.router, tags=["Recommendation"])

@app.get("/health")
async def health():
    return JSONResponse(status_code=200, content={"message": "OK"})

