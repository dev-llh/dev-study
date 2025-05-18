package study.week18.observerpattern.post.observer;

import org.springframework.stereotype.Component;
import study.week18.observerpattern.post.Post;

@Component
public class EmailNotifier implements PostObserver {
    @Override
    public void onPostCreated(Post post) {
        System.out.println("[EmailNotifier] 이메일 발송: " + post.getTitle());
    }
}
