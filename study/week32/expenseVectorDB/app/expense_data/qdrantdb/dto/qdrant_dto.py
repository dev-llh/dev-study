from typing import List
from uuid import uuid5, NAMESPACE_DNS, uuid4

from pydantic import BaseModel

from app.expense_data.dto.expense_dto import ExpenseSearchRequest
from app.expense_data.mongodb.model.expense_entity import Expense
from app.expense_data.qdrantdb.model.expense_vecotr_entity import ExpenseEntity


class ExpenseVectorSearchRequest(BaseModel):
    expense_text: str
    limit: int

    @classmethod
    def from_expense_search_request(cls, request: "ExpenseSearchRequest") -> "ExpenseVectorSearchRequest":
        return cls(
            expense_text=request.expense_text,
            limit=request.limit,
        )

class ExpenseVectorRequest(BaseModel):
    id: str
    vectorize_info: str
    account_year: int
    account_div_code: str
    account_div_name: str
    cost_div_code: str
    cost_div_name: str
    expense_amount: int
    manage_div_code: str
    manage_div_name: str

    @classmethod
    def from_expense(cls, expense: "Expense") -> "ExpenseVectorRequest":
        if expense.id:
            # ObjectId를 기반으로 고유한 UUID 생성 (namespace로 사용)
            expense_uuid = uuid5(NAMESPACE_DNS, str(expense.id))
        else:
            # 새로운 UUID 생성
            expense_uuid = uuid5(NAMESPACE_DNS, str(uuid4()))
        return cls(
            id=str(expense_uuid),
            vectorize_info=expense.summary_info,
            account_year=expense.account_year,
            account_div_code=expense.account_div_code,
            account_div_name=expense.account_div_name,
            cost_div_code=expense.cost_div_code,
            cost_div_name=expense.cost_div_name,
            expense_amount=expense.expense_amount,
        )

    @classmethod
    def from_expense_entity(cls, expense_enitity: "ExpenseEntity") -> "ExpenseVectorRequest":
        if expense_enitity.id:
            # ObjectId를 기반으로 고유한 UUID 생성 (namespace로 사용)
            expense_uuid = uuid5(NAMESPACE_DNS, str(expense_enitity.id))
        else:
            # 새로운 UUID 생성
            expense_uuid = uuid5(NAMESPACE_DNS, str(uuid4()))
        return cls(
            id=str(expense_uuid),
            vectorize_info=expense_enitity.summary_info,
            account_year=expense_enitity.account_year,
            account_div_code=expense_enitity.account_div_code,
            account_div_name=expense_enitity.account_div_name,
            cost_div_code=expense_enitity.cost_div_code,
            cost_div_name=expense_enitity.cost_div_name,
            expense_amount=expense_enitity.expense_amount,
            manage_div_code=expense_enitity.manage_div_code,
            manage_div_name=expense_enitity.manage_div_name
        )
