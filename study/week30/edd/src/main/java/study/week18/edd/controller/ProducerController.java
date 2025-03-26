package study.week18.edd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.week18.edd.dto.CommentDto;
import study.week18.edd.dto.CommentEvent;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final ApplicationEventPublisher eventPublisher;
    private final ObjectMapper objectMapper;

    @PostMapping("/event")
    public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto) throws Exception {
        CommentEvent event = new CommentEvent(objectMapper.writeValueAsString(commentDto));

        eventPublisher.publishEvent(event);
        return ResponseEntity.ok().build();
    }
}
