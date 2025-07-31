from transformers import AutoModelForSeq2SeqLM, AutoTokenizer
import torch
import asyncio
from typing import Optional

from config import settings

model_name = settings.translation_model
src_lang = "eng_Latn"
tgt_lang = "kor_Hang"

# 전역 모델/토크나이저 변수
model: Optional[AutoModelForSeq2SeqLM] = None
tokenizer: Optional[AutoTokenizer] = None
device = "cuda" if torch.cuda.is_available() else "cpu"

def _load_model_and_tokenizer():
    global model, tokenizer
    if model is None or tokenizer is None:
        model = AutoModelForSeq2SeqLM.from_pretrained(model_name).to(device)
        tokenizer = AutoTokenizer.from_pretrained(model_name)
    return model, tokenizer

def _translate_en_to_ko_sync(text: str) -> str:
    """영어 텍스트를 한국어로 동기 번역"""
    model, tokenizer = _load_model_and_tokenizer()
    tokenizer.src_lang = src_lang
    inputs = tokenizer(text, return_tensors="pt", padding=True, truncation=True).to(device)
    forced_bos_token_id = tokenizer.convert_tokens_to_ids(tgt_lang)
    generated_tokens = model.generate(
        **inputs,
        forced_bos_token_id=forced_bos_token_id,
        max_length=512,
        num_beams=4,
    )
    return tokenizer.batch_decode(generated_tokens, skip_special_tokens=True)[0]

def _translate_ko_to_en_sync(text: str) -> str:
    """한국어 텍스트를 영어로 동기 번역"""
    model, tokenizer = _load_model_and_tokenizer()
    tokenizer.src_lang = tgt_lang
    inputs = tokenizer(text, return_tensors="pt", padding=True, truncation=True).to(device)
    forced_bos_token_id = tokenizer.convert_tokens_to_ids(src_lang)
    generated_tokens = model.generate(
        **inputs,
        forced_bos_token_id=forced_bos_token_id,
        max_length=512,
        num_beams=4,
    )
    return tokenizer.batch_decode(generated_tokens, skip_special_tokens=True)[0]

async def translate_en_to_ko(text: str) -> str:
    """비동기 영어→한국어 번역 함수"""
    loop = asyncio.get_event_loop()
    return await loop.run_in_executor(None, _translate_en_to_ko_sync, text)

async def translate_ko_to_en(text: str) -> str:
    """비동기 한국어→영어 번역 함수"""
    loop = asyncio.get_event_loop()
    return await loop.run_in_executor(None, _translate_ko_to_en_sync, text)