package kr.taeu.jpapractice;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.taeu.jpapractice.team.model.QTeamEntity;
import kr.taeu.jpapractice.team.model.TeamEntity;
import kr.taeu.jpapractice.user.model.QUserEntity;
import kr.taeu.jpapractice.user.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "logging.level.org.hibernate.orm.jdbc.bind=trace"
})
@Import(JpaConfig.class)
public class QueryDSLTest {

    @Autowired
    EntityManager em;

    @Test
    void fetch() {
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

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QUserEntity qUser = QUserEntity.userEntity;

        List<UserEntity> users = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.name.eq("태우"))
                .fetch();

        Assertions.assertThat(users)
                .extracting(UserEntity::getName)
                .containsExactly("태우");
        //select ue1_0.user_id,ue1_0.name,ue1_0.team from user_entity ue1_0 where ue1_0.name=?
    }

    @Test
    void fetchNplusOne() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        TeamEntity team2 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user.setTeam(team);
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team2);
        em.persist(user);
        em.persist(user2);

        em.flush();
        em.clear();

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QUserEntity qUser = QUserEntity.userEntity;

        List<UserEntity> users = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.name.eq("태우"))
                .fetch();

        Assertions.assertThat(users)
                .extracting(UserEntity::getName)
                .allMatch(s -> s.equals("태우"));

        // N+1
        System.out.println("users = " + users);
    }

    @Test
    void leftJoinNonFetch() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        TeamEntity team2 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user.setTeam(team);
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team2);
        em.persist(user);
        em.persist(user2);

        em.flush();
        em.clear();

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QUserEntity qUser = QUserEntity.userEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;

        List<Tuple> fetch = jpaQueryFactory.select(qUser, qTeam)
                .from(qUser)
                .leftJoin(qUser.team, qTeam)
                .fetch();

        fetch.forEach(System.out::println);

//        Assertions.assertThat(users)
//                .extracting(UserEntity::getName)
//                .allMatch(s -> s.equals("태우"));
//
//        // N+1
//        System.out.println("users = " + users);
    }

    @Test
    void leftJoinFetch() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        TeamEntity team2 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user.setTeam(team);
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team2);
        em.persist(user);
        em.persist(user2);

        em.flush();
        em.clear();

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QUserEntity qUser = QUserEntity.userEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;

        List<UserEntity> users = jpaQueryFactory.selectFrom(qUser)
                .leftJoin(qUser.team, qTeam).fetchJoin()
                .fetch();

        Assertions.assertThat(users)
                .extracting(UserEntity::getName)
                .allMatch(s -> s.equals("태우"));

        // N+1
        System.out.println("users = " + users);
    }

    @Test
    void paging() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        TeamEntity team2 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user.setTeam(team);
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team2);
        em.persist(user);
        em.persist(user2);

        em.flush();
        em.clear();

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QUserEntity qUser = QUserEntity.userEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;

        Pageable pageable = PageRequest.of(1, 1);

        List<Tuple> fetch = jpaQueryFactory.select(qUser, qTeam)
                .from(qUser)
                .leftJoin(qUser.team, qTeam)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory.select(qUser.count())
                .from(qUser)
                .leftJoin(qUser.team, qTeam);

        Page<Tuple> page = PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne);

        System.out.println("page = " + page.getContent());
    }

    @Test
    void dynamicQuery() {
        TeamEntity team = TeamEntity.builder()
                .name("팀1")
                .build();

        TeamEntity team2 = TeamEntity.builder()
                .name("팀2")
                .build();

        UserEntity user = UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("twlee2")
                .name("태우")
                .build();
        user.setTeam(team);
        user2.setTeam(team2);
        em.persist(team);
        em.persist(team2);
        em.persist(user);
        em.persist(user2);

        em.flush();
        em.clear();

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QUserEntity qUser = QUserEntity.userEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;

        List<UserEntity> users = jpaQueryFactory.selectFrom(qUser)
                .leftJoin(qUser.team, qTeam).fetchJoin()
                .where(equalsTeam(1L))
                .fetch();

        System.out.println("users = " + users);
    }

    private BooleanExpression equalsTeam(Long teamId) {
        if (teamId == null) {
            return null;
        }
        return QUserEntity.userEntity.team.teamId.eq(teamId);
    }
}
