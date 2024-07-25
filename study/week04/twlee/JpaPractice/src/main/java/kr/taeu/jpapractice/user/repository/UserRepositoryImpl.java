package kr.taeu.jpapractice.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.taeu.jpapractice.user.model.QUserEntity;
import kr.taeu.jpapractice.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositorySupporter {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserEntity> findByNameCustom (String name) {
        return jpaQueryFactory.selectFrom(QUserEntity.userEntity)
                .where(QUserEntity.userEntity.name.eq(name))
                .fetch();
    }
}
