package study.week18.observerpattern.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import study.week18.observerpattern.comment.CommentService;
import study.week18.observerpattern.post.PostService;

@Controller
@RequiredArgsConstructor
public class controller {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.status(200).body("Hi");
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> post(@RequestBody String title) {
        return ResponseEntity.status(200).body(postService.createPost(title));
    }

    @PostMapping("/comment")
    public ResponseEntity<Boolean> comment(@RequestBody String title) {
        return ResponseEntity.status(200).body(commentService.addComment(title));
    }
}
