package study.week18.edd.dto;

import org.springframework.context.ApplicationEvent;

public class SendMailEvent extends ApplicationEvent {
    public SendMailEvent(Object source) {
        super(source);
    }

    public Object getSource() { return super.getSource(); }
}
