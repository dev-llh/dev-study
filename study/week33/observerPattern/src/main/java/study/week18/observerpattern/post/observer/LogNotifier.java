package study.week18.observerpattern.post.observer;

import org.springframework.stereotype.Component;
import study.week18.observerpattern.post.Post;

@Component
public class LogNotifier implements PostObserver {
    @Override
    public void onPostCreated(Post post) {
        System.out.println("[LogNotifier] 로그 기록: " + post.getTitle());
    }
}
