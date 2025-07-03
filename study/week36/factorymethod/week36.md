
# 팩토리 메서드 패턴 (Factory Method Pattern)

## 개념
팩토리 메서드 패턴은 객체 생성 로직을 서브클래스에 위임하여, 객체 생성의 캡슐화를 가능하게 해주는 생성 패턴(Creational Pattern)이다. 클라이언트 코드가 구체적인 클래스를 직접 생성하지 않고, 추상화된 인터페이스를 통해 객체를 생성할 수 있도록 해준다.

즉, 객체를 생성하는 코드를 별도의 메서드(팩토리 메서드)로 분리하여, 서브클래스가 이 메서드를 오버라이드하여 원하는 객체를 생성하게 한다.

## 키워드
- **객체 생성 캡슐화**
- **인터페이스/추상 클래스 기반 설계**
- **서브클래스에 생성 책임 위임**
- **유연성(Flexibility)**
- **확장성(Extensibility)**
- **OCP(개방-폐쇄 원칙) 준수**

## 구성 요소
- **Product (제품)**: 생성될 객체의 공통 인터페이스 혹은 추상 클래스
- **ConcreteProduct (구체적인 제품)**: Product를 구현한 실제 클래스
- **Creator (창조자)**: 팩토리 메서드를 정의하는 추상 클래스 또는 인터페이스
- **ConcreteCreator (구체적인 창조자)**: 팩토리 메서드를 오버라이드하여 특정 Product 객체를 생성하는 클래스

## 특징
- 객체 생성 과정을 서브클래스에 위임
- 새로운 타입의 객체 추가 시 Creator를 수정하지 않고 ConcreteCreator만 추가하면 됨
- 런타임 시점에 어떤 객체가 생성될지 유연하게 결정 가능
- 상속을 통해 객체 생성 방식을 확장할 수 있음

## 장점
1. **유연성 향상**: 클라이언트 코드가 구체 클래스를 몰라도 객체를 생성 가능
2. **OCP 준수**: 기존 코드를 변경하지 않고 새로운 객체 타입 추가 가능
3. **객체 생성 코드 분리**: 유지보수성과 테스트 용이성 증가
4. **코드 중복 최소화**: 공통 생성 로직은 상위 Creator에 작성 가능

## 단점
1. **클래스 수 증가**: 제품마다 Creator/ConcreteCreator 클래스를 새로 만들어야 할 수 있음
2. **복잡도 증가**: 단순한 경우에는 오히려 구조가 복잡해질 수 있음
3. **초기 설계 부담**: 패턴 구조가 명확히 필요하지 않으면 과한 설계가 될 수 있음

## 사용 사례
### 1. **GUI 컴포넌트 생성 (Java Swing, Android 등)**
- OS나 테마에 따라 Button, TextField 등을 다르게 생성할 필요가 있을 때
```java
abstract class Dialog {
    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }
    public abstract Button createButton();
}

class WindowsDialog extends Dialog {
    public Button createButton() {
        return new WindowsButton();
    }
}

class HtmlDialog extends Dialog {
    public Button createButton() {
        return new HtmlButton();
    }
}
```

### 2. **Spring Framework의 Bean 생성**
- ApplicationContext가 내부적으로 Factory Method를 이용해 Bean을 생성

### 3. **JDBC Connection 생성**
- `DriverManager.getConnection()` 내부에서 어떤 DB 연결을 할지 결정하여 적절한 드라이버 인스턴스를 생성

### 4. **게임에서 캐릭터, 무기, 스킬 생성**
- 팩토리를 이용해 직업별 캐릭터나 무기를 다르게 생성

### 5. **로그 시스템**
- 로깅 대상 (파일, 콘솔, 원격 서버 등)에 따라 서로 다른 Logger 인스턴스 생성

## 비교: 팩토리 메서드 vs 추상 팩토리
| 구분 | 팩토리 메서드 패턴 | 추상 팩토리 패턴 |
|------|----------------------|---------------------|
| 생성 방식 | 메서드를 오버라이드하여 객체 생성 | 관련 객체 집합을 생성하는 인터페이스 제공 |
| 목적 | 단일 객체 생성 책임 위임 | 관련된 객체들을 한 번에 생성 |
| 클래스 수 | 상대적으로 적음 | 상대적으로 많음 |
| 확장성 | 유연하지만 개별 객체 수준 | 제품군 전체를 쉽게 교체 가능 |

## 정리
팩토리 메서드 패턴은 객체 생성 로직을 서브클래스에 위임하여 유연성과 확장성을 확보하는 설계 패턴이다. 복잡한 객체 생성이 필요한 상황이나 객체 생성 시점에 유연한 결정이 필요할 때 적절하다. 단, 단순한 상황에서는 오히려 코드가 복잡해질 수 있으므로 상황에 맞게 적용해야 한다.
