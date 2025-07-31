from transformers import AutoModelForCausalLM, AutoTokenizer

from config import settings
import asyncio
import torch

torch.backends.cudnn.benchmark = True  # 입력 크기가 고정이면 성능 향상 가능

model_name = settings.llm_model

print("CUDA 버전:", torch.version.cuda)
print("GPU 사용 가능:", torch.cuda.is_available())
print("사용 중인 디바이스:", torch.cuda.get_device_name(0) if torch.cuda.is_available() else "None")
print("사용 중인 모델:", model_name)

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

tokenizer = AutoTokenizer.from_pretrained(
    model_name,
    trust_remote_code=True,
    use_fast=True,
)

model = AutoModelForCausalLM.from_pretrained(
    model_name,
    device_map="auto",  # 자동으로 GPU 활용
    torch_dtype=torch.float16 if torch.cuda.is_available() else torch.float32,
    trust_remote_code=True
)


model.eval()


conversation = [
    {
        "role": "system",
        "content": (
            "당신은 전문 위스키 소믈리에입니다. 아래 형식에 맞춰 사용자의 질문에 적합한 위스키 1~2개를 추천해 주세요.\n\n"
            
            "예시 형식:\n"
            "1. 글렌피딕 12년 - 부드럽고 달콤한 맛으로 위스키 입문자에게 적합합니다.\n"
            "2. 맥캘란 18년 - 풍부한 바닐라와 오크 향이 특징이며, 특별한 날에 어울립니다.\n\n"
            
            "조건:\n"
            "- 추천 이유를 간결하고 명확하게 작성해 주세요."
        )
    }
]

async def ask_llm(question: str) -> str:
    # 4. Mistral 모델은 apply_chat_template에 role 기반 메시지 리스트를 넘겨줘야 함
    conversation.append({"role": "user", "content": question})

    # 5. apply_chat_template 함수 호출로 prompt 템플릿에 맞춰 토크나이즈 + 텐서 변환
    inputs = tokenizer.apply_chat_template(
        conversation,
        add_generation_prompt=True,  # 모델에 따라 필요
        return_dict=True,
        return_tensors="pt",
    )

    # 6. 텐서를 모델이 위치한 device(GPU 등)로 이동
    inputs = {k: v.to(device) for k, v in inputs.items()}

    loop = asyncio.get_event_loop()

    def generate():
        # 7. no_grad 모드로 생성
        with torch.no_grad():
            output_ids = model.generate(
                **inputs,
                max_new_tokens=256,
                # temperature=0.0,
                do_sample=False,
                # do_sample=True,
                # top_k=50,
                # top_p=0.95,
                repetition_penalty=1.1,
                eos_token_id=tokenizer.eos_token_id,
                pad_token_id=tokenizer.eos_token_id,
            )

        # 8. 생성된 토큰을 텍스트로 디코딩
        decoded = tokenizer.decode(output_ids[0], skip_special_tokens=True)

        # 9. 전체 출력 결과 콘솔에 출력 (디버그용)
        print("모델 출력 전체:\n", decoded)
        return decoded

    # 10. 비동기 환경에서 동기 함수 실행
    response = await loop.run_in_executor(None, generate)

    last_user_content = conversation[-1]["content"]
    if last_user_content in response:
        response = response.split(last_user_content)[-1].strip()

    conversation.append({"role": "assistant", "content": response})
    return response