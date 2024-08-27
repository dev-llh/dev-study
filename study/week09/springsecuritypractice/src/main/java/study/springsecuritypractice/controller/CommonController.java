package study.springsecuritypractice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {

    @GetMapping("/")
    public ModelAndView hello() throws Exception {
        ModelAndView mav = new ModelAndView("hello");

        return mav;
    }

    @GetMapping("/main")
    public ModelAndView main(@AuthenticationPrincipal User user) throws Exception {
        ModelAndView mav = new ModelAndView("main");
        System.out.println(user+"@@");

        return mav;
    }

}
