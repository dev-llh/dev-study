from fastapi import FastAPI
from app.expense_data.routers import expense_data_router
import torch

app = FastAPI(title="Expense API")

app.include_router(expense_data_router.router, prefix="/expense/data", tags=["ExpenseData"])

@app.get("/health")
async def root():
    return {"message": "I'm alive!"}

@app.get("/device")
async def get_device():
    return {"device": "cuda" if torch.cuda.is_available() else "cpu"}
