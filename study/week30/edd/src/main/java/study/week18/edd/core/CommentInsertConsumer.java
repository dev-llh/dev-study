package study.week18.edd.core;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import study.week18.edd.dto.CommentInsertEvent;
import study.week18.edd.dto.SendPushEvent;

@Component
public class CommentInsertConsumer {
    @Async // 비동기 실행
    @EventListener
    public void consumeEvent(CommentInsertEvent event) throws Exception {
        Thread.sleep(7000);
        System.out.println("DB에 넣었음 : " + event.getSource().toString());
    }
}

