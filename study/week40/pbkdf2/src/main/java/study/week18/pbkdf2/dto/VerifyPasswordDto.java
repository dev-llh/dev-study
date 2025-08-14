package study.week18.pbkdf2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyPasswordDto {
    private String password;
    private String salt;
    private String hashedPassword;

}
