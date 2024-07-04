package kr.taeu.jpapractice.user.repository;

import kr.taeu.jpapractice.user.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUserIdWhenUserExistsThenReturnUser() {
        UserEntity saved = userRepository.save(UserEntity.builder()
                .userId("twlee")
                .name("hello")
                .build());

        UserEntity found = assertDoesNotThrow(() -> userRepository.findById("twlee").get());

        assertEquals(saved, found);
        assertEquals(saved.getUserId(), found.getUserId());
    }

}