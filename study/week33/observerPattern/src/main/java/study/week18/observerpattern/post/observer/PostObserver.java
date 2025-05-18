package study.week18.observerpattern.post.observer;

import study.week18.observerpattern.post.Post;

public interface PostObserver {
    void onPostCreated(Post post);
}
