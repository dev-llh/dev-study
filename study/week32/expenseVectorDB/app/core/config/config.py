from pydantic_settings import BaseSettings

class Settings(BaseSettings):
    mongodb_uri: str
    mongodb_db: str

    qdrant_host: str
    qdrant_port: int
    qdrant_collection: str

    open_api_url: str
    open_api_key: str

    class Config:
        env_file = ".env"

settings = Settings()