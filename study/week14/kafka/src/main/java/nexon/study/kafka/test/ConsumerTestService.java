package nexon.study.kafka.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerTestService {

    @KafkaListener(topics = "error-message", groupId = "error-handler-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
