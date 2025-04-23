from pydantic import BaseModel


class MongoExpenseRequest(BaseModel):
    account_year: int
    account_div_code: str
    account_div_name: str
    cost_div_code: str
    cost_div_name: str
    summary_info: str
    expense_amount: int