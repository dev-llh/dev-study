import asyncio
from typing import List

from app.expense_data.dto.expense_dto import ExpenseSearchRequest
from app.expense_data.mongodb.model.expense_entity import Expense
from app.expense_data.mongodb.service.mongo_service import save_expenses_to_db
from app.expense_data.openapi.dto.expense_dream_dto import ExpenseDreamRequest, ExpenseDreamResponse
from app.expense_data.openapi.service.external_api import fetch_external_expense_data
from app.expense_data.qdrantdb.dto.qdrant_dto import ExpenseVectorRequest, ExpenseVectorSearchRequest
from app.expense_data.qdrantdb.model.expense_vecotr_entity import ExpenseEntity
from app.expense_data.qdrantdb.service.qdrant_service import upsert_expenses_vector, search_expense_vector


async def fetch_expense_data(request: ExpenseDreamRequest) -> List[ExpenseDreamResponse]:
    return await fetch_external_expense_data(request)

async def migration_expense_data_optimizer():
    start_index = 0
    end_index = 731
    batch_size = 10

    for batch_start in range(start_index, end_index, batch_size):
        batch_end = min(batch_start + batch_size, end_index)

        requests = [ExpenseDreamRequest(pIndex=i) for i in range(batch_start + 1, batch_end + 1)]

        results = await asyncio.gather(*[
            fetch_external_expense_data(req) for req in requests
        ])

        # 결과 평탄화
        all_expense_data = [data for group in results for data in group]

        # 모델 변환
        expenses = [ExpenseEntity.from_dream_response(data) for data in all_expense_data]
        vector_requests = [ExpenseVectorRequest.from_expense_entity(exp) for exp in expenses]

        await upsert_expenses_vector(vector_requests)

async def migration_expense_data_index(index: int):

    all_saved_expenses = []

    limit = index * 10
    start = limit - 10

    while True:
        request = ExpenseDreamRequest(pIndex=start)
        expense_data = await fetch_external_expense_data(request)
        if len(expense_data) == 0:
            start += 1
            continue
        if start == limit:
            break
        expenses = [Expense.from_dream_response(data) for data in expense_data]
        all_saved_expenses.extend(await save_expenses_to_db(expenses))
        start += 1

    vector_requests = [ExpenseVectorRequest.from_expense(expense) for expense in all_saved_expenses]

    await upsert_expenses_vector(vector_requests)

    return all_saved_expenses

async def migration_expense_data_optimizer_index(index: int):

    limit = index * 20
    start = limit - 20

    # 요청들 미리 구성
    requests = [ExpenseDreamRequest(pIndex=i) for i in range(start+1, limit+1)]

    # 비동기로 병렬 fetch
    results = await asyncio.gather(*[
        fetch_external_expense_data(req) for req in requests
    ])

    # 결과 평탄화
    all_expense_data = [data for group in results for data in group]

    # 모델 변환
    expenses = [ExpenseEntity.from_dream_response(data) for data in all_expense_data]
    vector_requests = [ExpenseVectorRequest.from_expense_entity(exp) for exp in expenses]

    await upsert_expenses_vector(vector_requests)


async def search_expense_data_request(request: ExpenseSearchRequest):
    return await search_expense_vector(ExpenseVectorSearchRequest.from_expense_search_request(request))