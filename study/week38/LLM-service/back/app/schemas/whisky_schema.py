from pydantic import BaseModel, Field

class WhiskyData(BaseModel):
    id: int = Field(..., alias='')
    name: str
    category: str
    review_point: float = Field(..., alias='review.point')
    description: str
    description_ko: str

    class Config:
        allow_population_by_alias = True
