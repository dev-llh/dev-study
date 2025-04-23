from typing import List

from app.expense_data.mongodb.db.mogodb import expense_collection
from app.expense_data.mongodb.model.expense_entity import Expense



async def save_expense_to_db(expense: Expense) -> Expense:
    result = await expense_collection.insert_one(expense.dict(by_alias=True, exclude_unset=True))
    expense.id = result.inserted_id
    return expense

async def save_expenses_to_db(expenses: List[Expense]) -> List[Expense]:
    expense_dicts = [expense.dict(by_alias=True, exclude_unset=True) for expense in expenses]

    result = await expense_collection.insert_many(expense_dicts)

    for expense, inserted_id in zip(expenses, result.inserted_ids):
        expense.id = inserted_id

    return expenses