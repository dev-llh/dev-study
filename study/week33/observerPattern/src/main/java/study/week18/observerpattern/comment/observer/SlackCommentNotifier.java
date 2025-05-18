package study.week18.observerpattern.comment.observer;

import org.springframework.stereotype.Component;
import study.week18.observerpattern.comment.Comment;

@Component
public class SlackCommentNotifier implements CommentObserver {
    @Override
    public void onCommentAdded(Comment comment) {
        System.out.println("[SlackCommentNotifier] 슬랙 알림 발송: " + comment.getContent());
    }
}
