package study.week18.pbkdf2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import study.week18.pbkdf2.dto.GetHashedPasswordDto;
import study.week18.pbkdf2.dto.VerifyPasswordDto;
import study.week18.pbkdf2.util.PBKDF2Util;

@Controller
public class CommonController {

    @GetMapping("/getSalt")
    public ModelAndView getSalt() {
        return new ModelAndView("salt");
    }

    @PostMapping("/getSalt")
    public ResponseEntity<String> getSaltPost() throws Exception {
        return ResponseEntity.ok().body(PBKDF2Util.getRandomSalt());
    }

    @GetMapping("/getHashedPassword")
    public ModelAndView getHashedPassword() throws Exception {
        return new ModelAndView("hashPassword");
    }

    @PostMapping("/getHashedPassword")
    public ResponseEntity<String> getHashPasswordPost(@RequestBody GetHashedPasswordDto getHashedPasswordDto) throws Exception {
        String hashedPassword = PBKDF2Util.hashPassword(getHashedPasswordDto.getPassword(), getHashedPasswordDto.getSalt());
        return ResponseEntity.ok().body(hashedPassword);
    }

    @GetMapping("/verifyPassword")
    public ModelAndView verifyPassword() throws Exception {
        return new ModelAndView("verifyPassword");
    }

    @PostMapping("/verifyPassword")
    public ResponseEntity<String> verifyPasswordPost(@RequestBody VerifyPasswordDto verifyPasswordDto) throws Exception {
        boolean result = PBKDF2Util.verifyPassword(verifyPasswordDto.getPassword(), verifyPasswordDto.getSalt(), verifyPasswordDto.getHashedPassword());
        return ResponseEntity.ok().body(result ? "맞았다!" : "틀렸는디?");
    }
}
