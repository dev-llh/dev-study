package study.week18.edd.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import study.week18.edd.dto.*;

@Component
@RequiredArgsConstructor
public class EventRouter {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;

    @Async // 비동기 실행
    @EventListener
    public void handleEvent(ApplicationEvent event) throws Exception {

        if (event instanceof CommentEvent commentInsertEvent) {
            CommentDto commentDto = objectMapper.readValue(commentInsertEvent.getSource().toString(), CommentDto.class);

            applicationEventPublisher.publishEvent(new SendMailEvent(commentDto.registerUserId()));
            applicationEventPublisher.publishEvent(new SendPushEvent(commentDto.registerUserId()));
            applicationEventPublisher.publishEvent(new CommentInsertEvent(commentDto));

        } //...
    }
}
