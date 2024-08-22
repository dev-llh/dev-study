package kr.taeu.app;

import org.junit.jupiter.api.Test;

public class TextBlockTest {

    @Test
    void test() {
        // old
        String html = "<html>\n" +
                "   <body>\n" +
                "       <span>hello world</span>\n" +
                "   </body>\n" +
                "</html>\n";

        System.out.println("html = " + html);

        // new
        html = """
                <html>
                    <body>
                        <span>hello world</span>
                    </body>
                </html>
                """;

        System.out.println("html = " + html);
    }

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

    @Test
    void noTrim() {
        String str = """
                Apple(5)     \s
                Banana(1) \s
                Candy(2)  \s
                """;

        System.out.println("html = " + str);
    }
}
