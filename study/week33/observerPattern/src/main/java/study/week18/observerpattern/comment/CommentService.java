package study.week18.observerpattern.comment;

import org.springframework.stereotype.Service;
import study.week18.observerpattern.comment.observer.CommentObserver;

import java.util.List;

@Service
public class CommentService {

    private final List<CommentObserver> observers;

    public CommentService(List<CommentObserver> observers) {
        this.observers = observers;
    }

    public boolean addComment(String content) {
        Comment comment = new Comment(content);
        System.out.println("[CommentService] 댓글 작성됨: " + comment.getContent());

        for (CommentObserver observer : observers) {
            observer.onCommentAdded(comment);
        }

        return true;
    }
}
