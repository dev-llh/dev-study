package study.springsecuritypractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import study.springsecuritypractice.config.Role;
import study.springsecuritypractice.entity.UserEntity;
import study.springsecuritypractice.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() throws Exception {
        ModelAndView mav = new ModelAndView("login/login");

        return mav;
    }

    @GetMapping("/join")
    public ModelAndView join() throws Exception {
        ModelAndView mav = new ModelAndView("login/join");

        return mav;
    }

    @PostMapping("/join")
    public ModelAndView joinPost(@ModelAttribute UserEntity joinUser) throws Exception {

        joinUser.setRole(Role.USER);
        userService.saveUser(joinUser);

        ModelAndView mav = new ModelAndView("redirect:/login/login");
        return mav;
    }
}
