package study.week18.edd.core;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import study.week18.edd.dto.SendMailEvent;

@Component
public class SendMailConsumer {
    @Async // 비동기 실행
    @EventListener
    public void consumeEvent(SendMailEvent event) throws Exception {
        Thread.sleep(5000);
        System.out.println("메일 보냈음 : " + event.getSource().toString());

    }
}

