package study.week18.observerpattern.post.observer;

import org.springframework.stereotype.Component;
import study.week18.observerpattern.post.Post;

@Component
public class PushNotifier implements PostObserver {

    @Override
    public void onPostCreated(Post post) {
        System.out.println("알림 보냈음! " + post.getTitle());
    }

}
