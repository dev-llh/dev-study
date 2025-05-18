package study.week18.observerpattern.comment.observer;

import org.springframework.stereotype.Component;
import study.week18.observerpattern.comment.Comment;

@Component
public class EmailCommentNotifier implements CommentObserver {
    @Override
    public void onCommentAdded(Comment comment) {
        System.out.println("[EmailCommentNotifier] 댓글 이메일 발송: " + comment.getContent());
    }
}
