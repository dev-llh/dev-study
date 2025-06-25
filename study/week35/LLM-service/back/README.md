# LLM Service Backend

이 프로젝트는 LLM(Large Language Model) 기반의 위스키 서비스를 제공하는 FastAPI 백엔드 애플리케이션입니다.

## 주요 기능

- 데이터 마이그레이션
- 추천 시스템
- 임베딩 처리
- 번역 서비스
- LLM 통합

## 기술 스택

- **Framework**: FastAPI
- **Vector Database**: Qdrant
- **LLM & Embedding**: 
  - Transformers
  - Sentence Transformers
  - LangChain
- **데이터 처리**: Pandas
- **설정 관리**: Pydantic

## 프로젝트 아키텍처
![아키텍처도.png](%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98%EB%8F%84.png)
## 프로젝트 구조

```
.
├── app/
│   ├── api/            # API 엔드포인트
│   ├── core/           # 핵심 설정 및 유틸리티
│   ├── embedding/      # 임베딩 관련 기능
│   ├── llm/           # LLM 통합
│   ├── migration/     # 데이터 마이그레이션
│   ├── models/        # 데이터 모델
│   ├── schemas/       # Pydantic 스키마
│   └── translation/   # 번역 서비스
├── data/              # 데이터 파일
├── main.py           # 애플리케이션 진입점
├── config.py         # 설정 파일
└── requirements.txt  # 의존성 목록
```

## 시작하기

### 필수 요구사항

- Python 3.8 이상
- Docker 및 Docker Compose

### 설치


1. 의존성 설치
```bash
pip install -r requirements.txt
```

### 실행

Docker Compose를 사용하여 실행 (qdrant):
```bash
docker-compose up -d
```

## API 엔드포인트

- `GET /health`: 서비스 상태 확인
- Migration 관련 엔드포인트
- Recommendation 관련 엔드포인트


