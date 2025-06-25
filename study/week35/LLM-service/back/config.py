from pydantic_settings import BaseSettings

class Settings(BaseSettings):
    qdrant_host: str = "localhost"
    qdrant_port: int = 6333
    collection_en: str = "whisky_reviews_en"
    collection_ko: str = "whisky_reviews_ko"
    csv_path: str = "./data/scotch_review.csv"
    embedding_model: str = "intfloat/multilingual-e5-base"
    translation_model: str = "facebook/nllb-200-distilled-600M"
    llm_model: str = "beomi/KoAlpaca-Polyglot-5.8B"

    class Config:
        env_file = ".env"

settings = Settings()