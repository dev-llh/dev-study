from transformers import AutoTokenizer, AutoModel
import torch

model_name = "snunlp/KR-SBERT-V40K-klueNLI-augSTS"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModel.from_pretrained(model_name)

device = "cuda" if torch.cuda.is_available() else "cpu"

async def get_expense_embedding(text: str) -> list[float]:
    inputs = tokenizer(text, return_tensors="pt", truncation=True, padding=True).to(device)
    model.to(device)

    with torch.no_grad():
        outputs = model(**inputs)

    last_hidden_state = outputs.last_hidden_state  # [1, seq_len, hidden_size]
    attention_mask = inputs["attention_mask"]  # [1, seq_len]

    # 마스킹 평균
    mask = attention_mask.unsqueeze(-1).expand(last_hidden_state.size())
    masked_embeddings = last_hidden_state * mask
    sum_embeddings = masked_embeddings.sum(dim=1)
    sum_mask = mask.sum(dim=1)
    mean_pooled = sum_embeddings / sum_mask

    return mean_pooled.squeeze().tolist()
