package nexon.study.kafka.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalControllerAdvice {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e) {
        log.error("NullPointerException occurred", e);
        kafkaTemplate.send("error-message", String.format("%s >> %s", "NullPointerException occurred", e.getMessage()));
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
