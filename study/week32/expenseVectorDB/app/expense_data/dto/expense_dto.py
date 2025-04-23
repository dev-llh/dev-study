from pydantic import BaseModel


class ExpenseSearchRequest(BaseModel):
    expense_text: str
    limit: int