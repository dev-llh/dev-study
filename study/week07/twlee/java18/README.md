# JAVA 18 주요 변경점
## UTF-8 by Default
기본 문자셋을 OS 기본 언어에서 UTF-8로 변경함
## Simple Web Server
```bash
$ jwebserver
Binding to loopback by default. For all interfaces use "-b 0.0.0.0" or "-b ::".
Serving /cwd and subdirectories on 127.0.0.1 port 8000
URL: http://127.0.0.1:8000/
```
* 간편한 설정과 최소한의 기능만으로 즉시 사용 가능한 정적 HTTP 파일 서버를 제공
* 프로그래밍 방식의 생성과 사용자 정의를 위한 간단한 API와 함께 명령줄을 통해 기본 구현을 제공함