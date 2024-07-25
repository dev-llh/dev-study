package kr.taeu.jpapractice.user.repository;

import jakarta.persistence.EntityManager;
import kr.taeu.jpapractice.JpaConfig;
import kr.taeu.jpapractice.team.model.TeamEntity;
import kr.taeu.jpapractice.user.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaConfig.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

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

    @Test
    public void findByNameWhenUserExistsThenReturnUser() {
        UserEntity saved = userRepository.save(UserEntity.builder()
                .userId("twlee")
                .name("hello")
                .build());

        List<UserEntity> hello = assertDoesNotThrow(() -> userRepository.findByName("hello"));
        //select ue1_0.user_id,ue1_0.name,ue1_0.team from user_entity ue1_0 where ue1_0.name=?
        Assertions.assertThat(hello).hasSize(1);
        Assertions.assertThat(hello.getFirst()).isEqualTo(saved);

        System.out.println("hello = " + hello);
    }

    @Test
    public void findByName2WhenUserExistsThenReturnUser() {
        UserEntity saved = userRepository.save(UserEntity.builder()
                .userId("twlee")
                .name("hello")
                .build());

        List<UserEntity> hello = assertDoesNotThrow(() -> userRepository.findByName2("hello"));
        //select ue1_0.user_id,ue1_0.name,ue1_0.team from user_entity ue1_0 where ue1_0.name=?
        Assertions.assertThat(hello).hasSize(1);
        Assertions.assertThat(hello.getFirst()).isEqualTo(saved);

        System.out.println("hello = " + hello);
    }

    @Test
    public void updateName() {
        UserEntity saved = userRepository.save(UserEntity.builder()
                .userId("twlee")
                .name("hello")
                .build());

        int cnt = userRepository.updateName("twlee","testname");
        System.out.println("cnt = " + cnt);
        //Hibernate: update user_entity ue1_0 set name=? where ue1_0.user_id=?
        //cnt = 1
    }

    @Test
    public void customRepository() {

        UserEntity saved = userRepository.save(UserEntity.builder()
                .userId("twlee")
                .name("hello")
                .build());

        List<UserEntity> hello = userRepository.findByNameCustom("hello");

        System.out.println("hello = " + hello);
    }

    @Test
    public void entityGraph() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();
        em.persist(team);
        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("hello")
                .build();
        user.setTeam(team);
        UserEntity saved = userRepository.save(user);
        em.flush();

        UserEntity twlee = userRepository.findByUserId("twlee");
        System.out.println("twlee = " + twlee);
        // select ue1_0.user_id,ue1_0.name,t1_0.team_id,t1_0.name from user_entity ue1_0 left join team_entity t1_0 on t1_0.team_id=ue1_0.team where ue1_0.user_id=?
    }

}