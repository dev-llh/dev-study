# 벡터(Vector)

## 1. 벡터의 정의와 표현
벡터(Vector)는 크기와 방향을 가진 수학적 객체로, 공간 내의 위치를 나타내거나 물리적인 양을 표현하는 데 사용된다.

### 벡터의 표현 방법
1. **좌표 표현**: 벡터는 보통 n차원 공간에서 좌표로 나타낸다.
   - 예: 2차원 벡터 \( \mathbf{v} = (x, y) \), 3차원 벡터 \( \mathbf{v} = (x, y, z) \)
2. **단위 벡터(Unit Vector)**: 크기가 1인 벡터로 방향만을 나타낸다.
   - 예: \( \mathbf{u} = \frac{\mathbf{v}}{||\mathbf{v}||} \)
3. **기저 벡터(Basis Vector)**: 특정 공간을 생성하는 기본 벡터들로, 보통 표준 기저 벡터를 사용한다.
   - 예: 2차원에서는 \( \mathbf{e}_1 = (1, 0) \), \( \mathbf{e}_2 = (0, 1) \)

## 2. 벡터 연산

### 벡터 덧셈과 뺄셈
두 벡터 \( \mathbf{a} \)와 \( \mathbf{b} \)의 덧셈과 뺄셈은 각 성분별로 연산한다.
- \( \mathbf{a} + \mathbf{b} = (a_1 + b_1, a_2 + b_2, ..., a_n + b_n) \)
- \( \mathbf{a} - \mathbf{b} = (a_1 - b_1, a_2 - b_2, ..., a_n - b_n) \)

### 스칼라 곱 (Scalar Multiplication)
벡터에 스칼라(숫자)를 곱하면 크기가 조정된다.
- \( c \cdot \mathbf{a} = (c a_1, c a_2, ..., c a_n) \)

### 벡터 크기 (Norm)
벡터의 크기는 다음과 같이 계산된다.
- **유클리드 노름 (L2 노름)**: \( ||\mathbf{v}|| = \sqrt{x^2 + y^2 + z^2} \)
- **맨해튼 노름 (L1 노름)**: \( ||\mathbf{v}||_1 = |x| + |y| + |z| \)

## 3. 벡터 내적과 외적

### 내적 (Dot Product)
두 벡터 \( \mathbf{a} \)와 \( \mathbf{b} \)의 내적은 다음과 같이 계산된다.
- \( \mathbf{a} \cdot \mathbf{b} = a_1b_1 + a_2b_2 + ... + a_n b_n \)
- **기하학적 의미**: 두 벡터의 방향이 얼마나 유사한지를 측정하며, \( \cos \theta \)와 연관됨.
- \( \mathbf{a} \cdot \mathbf{b} = ||\mathbf{a}|| ||\mathbf{b}|| \cos \theta \)

### 외적 (Cross Product)
외적은 3차원 공간에서 정의되며, 두 벡터가 이루는 평면에 수직인 벡터를 반환한다.
- \( \mathbf{a} \times \mathbf{b} = (a_2b_3 - a_3b_2, a_3b_1 - a_1b_3, a_1b_2 - a_2b_1) \)
- **기하학적 의미**: 외적의 결과 벡터는 두 벡터가 이루는 평면에 수직이며, 크기는 평행사변형의 면적을 나타낸다.

## 📌 Python을 활용한 벡터 연산 실습

```python
import numpy as np

# 벡터 정의
a = np.array([1, 2, 3])
b = np.array([4, 5, 6])

# 벡터 덧셈 및 뺄셈
vector_addition = a + b
vector_subtraction = a - b

# 스칼라 곱
scalar_multiplication = 2 * a

# 벡터 내적
dot_product = np.dot(a, b)

# 벡터 외적
cross_product = np.cross(a, b)

# 결과 출력
print("벡터 덧셈:", vector_addition)
print("벡터 뺄셈:", vector_subtraction)
print("스칼라 곱:", scalar_multiplication)
print("벡터 내적:", dot_product)
print("벡터 외적:", cross_product)
```

---
이 문서는 벡터의 기본 개념과 연산, 내적과 외적에 대해 정리한 내용입니다. 추가적인 실습 코드가 필요하면 알려주세요! 😊

