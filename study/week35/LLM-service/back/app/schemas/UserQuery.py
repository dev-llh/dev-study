from pydantic import BaseModel, Field
from typing import Optional
from datetime import datetime

class UserQuery(BaseModel):
    userId: Optional[str] = Field(None, description="유저의 고유 ID")
    query: str = Field(..., description="유저의 질의 내용")
    timestamp: Optional[datetime] = Field(None, description="요청 시각")
    language: Optional[str] = Field(None, description="질의 언어 (예: 'ko', 'en')")
