from fastapi import APIRouter, Depends
from starlette.responses import JSONResponse
from app.schemas.UserQuery import UserQuery
from app.llm.llm_handler import ask_llm

router = APIRouter(prefix="/recommend", tags=["Recommendation"])

@router.get("/test")
async def test(query: str = Depends(UserQuery)):
    # UserQuery 객체에서 query 추출
    question = query.query
    # LLM에 질의
    answer = await ask_llm(question)
    return JSONResponse(status_code=200, content={"answer": answer})

@router.get("/whisky")
async def recommend():
    print("Recommend")
