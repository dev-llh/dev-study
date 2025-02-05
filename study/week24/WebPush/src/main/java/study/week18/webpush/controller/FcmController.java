package study.week18.webpush.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import study.week18.webpush.dto.RequestPushDto;
import study.week18.webpush.service.FCMService;

@Controller
public class FcmController {

    public FcmController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    private final FCMService fcmService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @PostMapping("/request-push")
    public ResponseEntity<String> requestPush(@RequestBody RequestPushDto requestPushDto) throws Exception {
        return ResponseEntity.ok(fcmService.sendMessageTo(requestPushDto));
    }
}
