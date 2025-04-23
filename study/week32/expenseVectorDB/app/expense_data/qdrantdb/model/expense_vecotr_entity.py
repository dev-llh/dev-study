from typing import List, Optional

from pydantic import BaseModel

from app.expense_data.openapi.dto.expense_dream_dto import ExpenseDreamResponse


class ExpenseVector(BaseModel):
    id: str
    embedding: List[float]
    metadata: dict



class ExpenseEntity(BaseModel):
    id: Optional[str] = None
    account_year: int
    account_div_code: str
    account_div_name: str
    cost_div_code: str
    cost_div_name: str
    summary_info: str
    expense_amount: int
    manage_div_code: str
    manage_div_name: str

    @classmethod
    def from_dream_response(cls, response: "ExpenseDreamResponse") -> "ExpenseEntity":
        return cls(**response.dict(exclude_unset=True, by_alias=False))