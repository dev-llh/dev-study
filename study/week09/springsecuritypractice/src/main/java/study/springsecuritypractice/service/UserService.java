package study.springsecuritypractice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.springsecuritypractice.entity.UserEntity;
import study.springsecuritypractice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("유저가 없음");
        }

        return User.builder().username(userEntity.getUsername()).password("{noop}"+userEntity.getPassword()).roles(userEntity.getRole().name()).build();
    }

    public UserEntity saveUser(UserEntity joinUser) throws Exception {

        return userRepository.save(joinUser);
    }
}
