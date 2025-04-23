# 🔍 기술 스택 개요 - 벡터 기반 유사도 검색 시스템

---

## 🚀 FastAPI

### 📌 개요
FastAPI는 Python 3.6+에서 사용할 수 있는 **고성능 비동기 웹 프레임워크**입니다. 타입 힌트를 기반으로 API 서버를 빠르고 효율적으로 개발할 수 있도록 설계되었습니다.

### 🛠 주요 특징
- **자동 문서화**: Swagger UI 및 Redoc을 통한 API 문서 자동 생성
- **타입 기반 검증**: Python의 타입 힌트를 기반으로 요청 데이터 자동 검증
- **비동기 지원**: `async` 키워드 기반의 비동기 프로그래밍
- **고성능**: Uvicorn과 함께 사용 시 매우 빠른 응답 처리 가능
- **사용자 친화적 문법**: 직관적이고 간결한 API 정의

### ✅ 사용 이유
- 빠르고 직관적인 API 서버 개발이 가능함
- OpenAPI 문서화 자동 지원으로 프론트엔드와의 협업 효율 향상
- 비동기 처리 기반으로 높은 동시성 지원

## ⚙️ Uvicorn

### 📌 개요
Uvicorn은 **ASGI (Asynchronous Server Gateway Interface)** 서버로, FastAPI와 같은 비동기 웹 프레임워크를 실행하기 위해 사용됩니다. 고성능 비동기 서버로, Python의 `async/await` 구조를 완벽히 지원합니다.

### 🛠 주요 특징
- **비동기 처리 최적화** (uvloop 기반)
- **HTTP/1.1, HTTP/2, WebSocket 지원**
- **Hot Reload 기능** (`--reload` 옵션)
- **경량 서버**로 빠른 시작과 응답 속도 보장

### ✅ 사용 이유
- FastAPI는 ASGI 기반 프레임워크이기 때문에 **ASGI 서버가 필수**
- 개발 중 자동 재시작 기능으로 생산성 향상
- 프로덕션에서는 **Gunicorn + Uvicorn worker** 조합으로도 활용 가능

## 🔄 Uvicorn vs Gunicorn

FastAPI와 같은 비동기 프레임워크를 실제 운영 환경에 배포할 때 자주 등장하는 서버 구성 요소는 **Uvicorn**과 **Gunicorn**입니다. 이 두 서버의 차이점과 역할을 명확히 이해하는 것은 고성능 API 서버 운영에 있어 중요합니다.


### 📊 주요 차이점 비교

| 항목           | Uvicorn                                | Gunicorn                                 |
|----------------|----------------------------------------|-------------------------------------------|
| 서버 타입      | ASGI (비동기 지원)                    | WSGI (동기 전용)                          |
| 사용 목적      | 비동기 웹 프레임워크 실행용            | 전통적인 동기 프레임워크 실행용           |
| 사용 대상      | FastAPI, Starlette 등 ASGI 프레임워크  | Django, Flask 등 WSGI 프레임워크         |
| 멀티 프로세스  | ❌ 기본적으로 단일 프로세스             | ✅ 멀티 프로세스 기반 (worker 관리 가능)  |
| 비동기 지원    | ✅ 완전한 지원 (`async/await`)         | ❌ 지원하지 않음                          |
| 실행 방식      | 단독 실행 가능                         | 단독 사용 시 비동기 프레임워크와 호환 불가 |
| 통합 방식 예시 | 단독 또는 Gunicorn과 함께 사용 가능    | Gunicorn + `uvicorn.workers.UvicornWorker` 조합 추천 |


---

## 🧠 Qdrant (Vector Database)

### 📌 개요
Qdrant는 **고차원 벡터 임베딩 기반의 검색을 지원하는 벡터 데이터베이스**입니다. 문장, 이미지 등 다양한 데이터를 벡터화하여 저장하고, 유사한 데이터를 빠르게 검색할 수 있도록 도와줍니다.

### 🛠 주요 특징
- **유사도 계산 지원**: Cosine, Euclidean, Dot Product 기반 검색
- **HNSW 인덱스 기반 고속 검색**
- **메타데이터 필터링** 지원 (벡터 + 조건 검색)
- **REST/gRPC API 및 Python SDK 제공**
- **고성능 및 스케일 확장성 보장**

### ✅ 사용 이유
- 수십만~수백만 개의 문장 임베딩 벡터 저장 및 검색 가능
- 텍스트, 이미지, 추천 시스템 등 유사도 기반 검색에 최적화
- 벡터 + 메타데이터 조건 검색 가능

---

## 🧬 KR-SBERT-V40K-klueNLI-augSTS (한국어 문장 임베딩 모델)

### 📌 개요
`KR-SBERT-V40K-klueNLI-augSTS`는 **한국어 특화 Sentence-BERT 모델**입니다. 문장을 고차원 벡터로 임베딩하여 문장 간 유사도 비교가 가능하도록 학습된 모델입니다.

### 🧠 학습 기반
- **KLUE-NLI**: 한국어 자연어 추론 데이터셋
- **STS + augSTS**: 의미 유사도 태스크에 특화된 학습 데이터

### 🛠 기술 사양
- SBERT 기반의 twin-tower 아키텍처
- 문장 임베딩 차원: 768
- 벡터화 방식: Mean Pooling
- 출력: 문장 임베딩 벡터 (Cosine 유사도 계산 가능)

### ✅ 사용 이유
- **의미 기반 문장 유사도 비교** 가능 (단순 키워드 매칭보다 정밀함)
- **한국어 특화** 학습으로 높은 표현력
- 추천 시스템, 중복 문장 탐지, 자연어 검색 등 다양한 태스크에 활용 가능

---

## 📚 참고 자료
- FastAPI 공식 문서: [https://fastapi.tiangolo.com](https://fastapi.tiangolo.com)
- Qdrant 공식 문서: [https://qdrant.tech](https://qdrant.tech)
- KR-SBERT 모델 정보: [https://huggingface.co/snunlp/KR-SBERT-V40K-klueNLI-augSTS](https://huggingface.co/snunlp/KR-SBERT-V40K-klueNLI-augSTS)

---

