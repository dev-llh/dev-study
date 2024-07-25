package kr.taeu.jpapractice;

import jakarta.persistence.EntityManager;
import kr.taeu.jpapractice.team.model.TeamEntity;
import kr.taeu.jpapractice.user.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaConfig.class)
@TestPropertySource(properties = {
        "logging.level.org.hibernate.orm.jdbc.bind=trace"
})
public class EntityManagerTest {

    @Autowired
    EntityManager em;

    @Test
    void findUser() {
        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        em.persist(user);

        UserEntity found = em.find(UserEntity.class, "twlee");

        Assertions.assertThat(found).isEqualTo(user);
    }


    @Test
    void findUserWithNativeQuery() {
        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        em.persist(user);

        UserEntity user2 = UserEntity.builder()
                .userId("test2")
                .name("테스트2")
                .build();
        em.persist(user2);

        List<UserEntity> users = em.createNativeQuery("SELECT USER_ID, NAME, TEAM FROM USER_ENTITY", UserEntity.class).getResultList();

        Assertions.assertThat(users).contains(user, user2);
    }

    @Test
    void findUserNoFetch() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);
        em.persist(team);
        em.persist(user);

        em.flush();
        em.clear();

        TeamEntity t = em.createQuery("SELECT t FROM TeamEntity t join t.users u", TeamEntity.class)
                .getSingleResult();
        /*
        Hibernate: select te1_0.team_id,te1_0.name from team_entity te1_0 join user_entity u1_0 on te1_0.team_id=u1_0.team
        Hibernate: select u1_0.team,u1_0.user_id,u1_0.name from user_entity u1_0 where u1_0.team=?
         */
        System.out.println("found = " + t.getUsers());
    }

    @Test
    void findUserFetch() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);
        em.persist(team);
        em.persist(user);

        em.flush();
        em.clear();

        TeamEntity t = em.createQuery("SELECT t FROM TeamEntity t left join fetch t.users u", TeamEntity.class)
                .getSingleResult();
        /*
        Hibernate: select te1_0.team_id,te1_0.name,u1_0.team,u1_0.user_id,u1_0.name from team_entity te1_0 join user_entity u1_0 on te1_0.team_id=u1_0.team
         */
        System.out.println("found = " + t.getUsers());
    }

    @Test
    void findUserFetchWhere() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);
        em.persist(team);
        em.persist(user);

        em.flush();
        em.clear();

        UserEntity u = em.createQuery("SELECT u FROM UserEntity u join fetch u.team t where t.name = '팀1'", UserEntity.class)
                .getSingleResult();

        System.out.println("found = " + u);
    }

    @Test
    void findUserFetchOn() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);
        em.persist(team);
        em.persist(user);

        em.flush();
        em.clear();

        Assertions.assertThatThrownBy(() -> em.createQuery("SELECT u FROM UserEntity u join fetch u.team t on t.name = '팀1'", UserEntity.class));
        // Fetch join has a 'with' clause (use a filter instead) [SELECT u FROM UserEntity u join fetch u.team t on t.name = '팀1']
    }

    @Test
    void namedQuery() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);
        em.persist(team);
        em.persist(user);

        em.flush();
        em.clear();

        List<UserEntity> users = em.createNamedQuery("UserEntity.findByName2", UserEntity.class)
                .setParameter("name", "태우")
                .getResultList();
        System.out.println("found = " + users);
    }



    @Test
    void detachTest() {
        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        em.persist(user);

        UserEntity found = em.find(UserEntity.class, "twlee");

        Assertions.assertThat(found).isEqualTo(user);

        em.detach(found);

        found.getTeam();
        Assertions.assertThat(found.getTeam()).isNull();
    }

    @Test
    void eagerTest() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);

        TeamEntity team1 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user1 = UserEntity.builder()
                .userId("twlee1")
                .name("태우")
                .build();
        user1.setTeam(team1);

        TeamEntity team2 = TeamEntity.builder()
                .name("팀3")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team1);
        em.persist(team2);
        em.persist(user);
        em.persist(user1);
        em.persist(user2);
        em.flush();
        em.clear();

        List<UserEntity> list = em.createQuery("select u from UserEntity u", UserEntity.class).getResultList();

        System.out.println("list = " + list);
    }

    @Test
    void fetchJoinTest() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();
        user.setTeam(team);

        TeamEntity team1 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user1 = UserEntity.builder()
                .userId("twlee1")
                .name("태우")
                .build();
        user1.setTeam(team1);

        TeamEntity team2 = TeamEntity.builder()
                .name("팀3")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team1);
        em.persist(team2);
        em.persist(user);
        em.persist(user1);
        em.persist(user2);
        em.flush();
        em.clear();

        List<UserEntity> list = em.createQuery("select u from UserEntity u join Fetch u.team", UserEntity.class).getResultList();

        System.out.println("list = " + list);
    }
}
