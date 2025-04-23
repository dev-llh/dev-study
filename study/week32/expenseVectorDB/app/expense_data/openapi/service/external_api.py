import httpx
from pydantic import parse_obj_as

from app.core.config.config import settings
from app.expense_data.openapi.dto.expense_dream_dto import ExpenseDreamRequest, ExpenseDreamResponse
from typing import List


async def fetch_external_expense_data(request: ExpenseDreamRequest) -> List[ExpenseDreamResponse]:
    try:
        async with httpx.AsyncClient() as client:
            response = await client.get(settings.open_api_url, params=request.dict())
            response.raise_for_status()  # 오류 발생 시 예외 발생

            data = response.json()
            expense_data = data.get("ExpendtrExcutAmtCost", [])
            row_data = expense_data[1].get("row", []) if len(expense_data) > 1 else []

            return parse_obj_as(List[ExpenseDreamResponse], row_data)

    except Exception as e:
        print(f"[ERROR] fetch failed for index {request.pIndex}: {e}")
        return []  # 실패한 경우에도 흐름이 끊기지 않도록 빈 리스트 반환