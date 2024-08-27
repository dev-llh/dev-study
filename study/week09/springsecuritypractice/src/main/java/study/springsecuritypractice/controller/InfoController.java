package study.springsecuritypractice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/employee")
    public ModelAndView employee(@AuthenticationPrincipal User user) throws Exception {
        ModelAndView mav = new ModelAndView("info/employee");
        mav.addObject("userInfo", user);

        return mav;
    }


}
