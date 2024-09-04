package study.springsecuritypractice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.springsecuritypractice.entity.UserEntity;
import study.springsecuritypractice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserEntity saveUser(UserEntity joinUser) throws Exception {
        UserEntity userEntity = userRepository.findByUsername(joinUser.getUsername());

        if (userEntity != null) {
            throw new RuntimeException("유저가 이미 존재함");
        }

        joinUser.setPassword(passwordEncoder.encode(joinUser.getPassword()));
        return userRepository.save(joinUser);
    }
}
