package study.week18.observerpattern.post;

import org.springframework.stereotype.Service;
import study.week18.observerpattern.post.observer.PostObserver;

import java.util.List;

@Service
public class PostService {

    private final List<PostObserver> observers;

    public PostService(List<PostObserver> observers) {
        this.observers = observers;
    }

    public Boolean createPost(String title) {
        Post post = new Post(title);
        System.out.println("[PostService] 게시글 생성: " + post.getTitle());

        // 옵저버들에게 알림
        for (PostObserver observer : observers) {
            observer.onPostCreated(post);
        }

        return true;
    }
}
