from pydantic import BaseModel, Field

from app.core.config.config import settings

class ExpenseDreamRequest(BaseModel):
    KEY: str = Field(default_factory=lambda: settings.open_api_key)
    pIndex: int = 1
    Type: str = "json"

class ExpenseDreamResponse(BaseModel):
    # 회계연도
    account_year: int = Field(..., alias="ACCNUT_YY")
    # 회계구분코드
    account_div_code: str = Field(..., alias="ACCNUT_DIV_CD")
    # 회계구분명
    account_div_name: str = Field(..., alias="ACCNUT_DIV_NM")
    # 경비구분코드
    cost_div_code: str = Field(..., alias="COST_DIV_CD_NM")
    # 경비구분명
    cost_div_name: str = Field(..., alias="COST_DIV_NM")
    # 지출개요
    summary_info: str = Field(..., alias="SUMMRY_INFO")
    # 지출금액
    expense_amount: int = Field(..., alias="EXPENDTR_RSLTN_AMT")
    # 통계목 코드
    manage_div_code: str = Field(..., alias="ANEXPTR_MANAGE_DIV_CD")
    # 통계목 명
    manage_div_name: str = Field(..., alias="ANEXPTR_MANAGE_TAXITM_DIV_NM")