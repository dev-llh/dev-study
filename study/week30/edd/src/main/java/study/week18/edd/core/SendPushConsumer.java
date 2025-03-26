package study.week18.edd.core;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import study.week18.edd.dto.SendPushEvent;

@Component
public class SendPushConsumer {
    @Async // 비동기 실행
    @EventListener
    public void consumeEvent(SendPushEvent event) throws Exception {
        Thread.sleep(6000);
        System.out.println("푸시 보냈음 : " + event.getSource().toString());

    }
}

