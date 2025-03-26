package study.week18.edd.dto;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CommentEvent extends ApplicationEvent {

    public CommentEvent(Object source) {
        super(source);
    }

    public Object getSource() { return super.getSource(); }
}
