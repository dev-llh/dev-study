package study.week18.edd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 비동기 활성화
@SpringBootApplication
public class EddApplication {

	public static void main(String[] args) {
		SpringApplication.run(EddApplication.class, args);
	}

}
