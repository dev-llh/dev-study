# JAVA 11 주요 변경점
## HttpClient 내장
* 기존 apache의 httpClient와는 이름만 같음
* 기본 제공 가용성과 가벼움이 장점, HttpURLConnection을 대체함
* Non Blocking 지원
* WebSocket 지원
* Factory Method 형태로 이용

```java
    @Test
    void old() throws IOException {
        URL url = new URL("http://www.google.com");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    
        StringBuilder responseData = getResponseData(httpURLConnection.getInputStream());
        System.out.println("responseData = " + responseData.toString());
    }
    
    public StringBuilder getResponseData(InputStream in) {
        if(in == null ) return null;
    
        StringBuilder sb = new StringBuilder();
        String line = "";
    
        try (InputStreamReader ir = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(ir)){
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    @Test
    void httpClient() throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://www.google.com"))
                    .build();

            String body = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
                    .body();

            System.out.println("body = " + body);
        }
    }
```

### String 관련 유틸 메서드 추가
* isBlank() - 비거나 스페이스로만 이뤄졌을때 true
* lines() - 줄단위 분리 후 Stream 형태로 반환
* strip(), stripLeading(), stripTrailing() - 문자열의 앞뒤 앞쪽 뒤쪽 공백 제거

### Java 10의 var 키워드를 람다에서 사용가능
```java
    @Test
void test() {
    List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h");

    List<String> collect = list.stream()
            .map((var s) -> s + "-")
            .toList();

    System.out.println("collect = " + collect);
}
```

### java 로 바로 파일 실행 가능
```bash
# old
javac Welcome.java
java Welcome

# new
java Welcome.java
```