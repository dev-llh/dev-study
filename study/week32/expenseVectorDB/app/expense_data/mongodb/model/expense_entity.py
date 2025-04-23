from typing import Optional

from bson import ObjectId
from pydantic import BaseModel, Field

from app.expense_data.openapi.dto.expense_dream_dto import ExpenseDreamResponse


class PyObjectId(ObjectId):
    @classmethod
    def __get_validators__(cls):
        yield cls.validate

    @classmethod
    def validate(cls, v):
        if not ObjectId.is_valid(v):
            raise ValueError("Invalid ObjectId")
        return ObjectId(v)

class Expense(BaseModel):
    id: Optional[PyObjectId] = Field(default=None, alias="_id")
    account_year: int
    account_div_code: str
    account_div_name: str
    cost_div_code: str
    cost_div_name: str
    summary_info: str
    expense_amount: int

    @classmethod
    def from_dream_response(cls, response: "ExpenseDreamResponse") -> "Expense":
        return cls(**response.dict(exclude_unset=True, by_alias=False))

    class Config:
        json_encoders = {
            ObjectId: str
        }
        populate_by_name = True
        validate_by_name = True