package study.week18.webrtc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView index() throws Exception {
        return new ModelAndView("index");
    }
}
