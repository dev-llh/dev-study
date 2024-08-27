package study.springsecuritypractice.vo;

import lombok.*;
import study.springsecuritypractice.config.Role;
import study.springsecuritypractice.entity.UserEntity;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserVO {

    private String username;
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
