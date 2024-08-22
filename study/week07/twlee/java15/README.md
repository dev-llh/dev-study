# JAVA 15 주요 변경점
## Hidden Classes
다른 class의 bytecode에서 직접 사용할 수 없는 class, 이는 런타임에 class를 생성하고(동적) 리플렉션으로 사용하는 프레임워크 레벨에서 사용하기 위한 것

## Text Blocks
java 13 Preview 기능이 정식 편입됨
```java
@Test
void test() {
    // old
    String html = "<html>\n" +
            "   <body>\n" +
            "       <span>hello world</span>\n" +
            "   </body>\n" +
            "</html>\n";

    // new
    html = """
            <html>
                <body>
                    <span>hello world</span>
                </body>
            </html>
            """;
}
```
java 14에서는 `line-terminator` 기능으로 개행문제를 억제할 수 있다.
```java
@Test
void lineTerminatorTest() {
    // old
    String html = "<html>" +
            "   <body>" +
            "       <span>hello world</span>" +
            "   </body>" +
            "</html>";

    System.out.println("html = " + html);

    // new
    html = """
            <html> \
                <body> \
                    <span>hello world</span> \
                </body> \
            </html> \
            """;

    System.out.println("html = " + html);
}
```
또한 `\s` 추가하여 공백 제거도 방지 할 수 있다.
```java
@Test
void noTrim() {
    String str = """
            Apple(5)     \s
            Banana(1) \s
            Candy(2)  \s
            """;

    System.out.println("html = " + str);
}
```
