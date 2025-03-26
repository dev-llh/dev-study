package study.week18.edd.dto;

import org.springframework.context.ApplicationEvent;

public class SendPushEvent extends ApplicationEvent {

    public SendPushEvent(Object source) {
        super(source);
    }

    public Object getSource() { return super.getSource(); }
}
