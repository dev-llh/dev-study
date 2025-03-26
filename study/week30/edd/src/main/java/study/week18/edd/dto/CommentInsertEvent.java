package study.week18.edd.dto;

import org.springframework.context.ApplicationEvent;

public class CommentInsertEvent extends ApplicationEvent {
    public CommentInsertEvent(Object source) {
        super(source);
    }

    public Object getSource() { return super.getSource(); }
}
