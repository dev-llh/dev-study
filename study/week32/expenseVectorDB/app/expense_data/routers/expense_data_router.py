from typing import List
from fastapi import APIRouter, Query
from starlette.responses import JSONResponse

from app.expense_data.dto.expense_dto import ExpenseSearchRequest
from app.expense_data.openapi.dto.expense_dream_dto import ExpenseDreamRequest, ExpenseDreamResponse
from app.expense_data.service.expense_data_service import fetch_external_expense_data, migration_expense_data_optimizer, \
     search_expense_data_request, migration_expense_data_optimizer_index

router = APIRouter()

@router.post("")
async def migration_dream_data():
    return await migration_expense_data_optimizer()

@router.get("/search")
async def search_expense_data(
        expense_text: str = Query("경비신청 정보", description="검색할 텍스트"),
        limit: int = Query(5, description="검색 결과 개수")
):
    search_result = await search_expense_data_request(ExpenseSearchRequest(expense_text=expense_text, limit=limit))
    serialized_result = [point.dict() for point in search_result]

    return JSONResponse(content=serialized_result)

@router.get("/{index}", response_model=List[ExpenseDreamResponse])
async def get_dream_data(index: int) -> List[ExpenseDreamResponse]:
    request = ExpenseDreamRequest(pIndex=index)
    return await fetch_external_expense_data(request)

@router.post("/{index}")
async def migration_dream_data(index: int):
    # return await migration_expense_data_index(index)
    await migration_expense_data_optimizer_index(index)

