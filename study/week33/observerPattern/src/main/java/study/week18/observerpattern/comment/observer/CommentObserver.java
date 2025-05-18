package study.week18.observerpattern.comment.observer;

import study.week18.observerpattern.comment.Comment;

public interface CommentObserver {
    void onCommentAdded(Comment comment);
}