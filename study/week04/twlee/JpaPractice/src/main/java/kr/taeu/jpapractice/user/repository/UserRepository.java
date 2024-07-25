package kr.taeu.jpapractice.user.repository;

import kr.taeu.jpapractice.user.model.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String>, UserRepositorySupporter {
    List<UserEntity> findByName(String name);

    List<UserEntity> findByName2(String name);

    @Query("select u from UserEntity u where u.userId = ?1")
    UserEntity findByUserId2(String userId);

    @Modifying
    @Query("update UserEntity u set u.name = :name where u.userId = :userId")
    int updateName(@Param("userId") String userId, @Param("name") String name);

    @EntityGraph(value = "UserEntity.withTeam")
    UserEntity findByUserId(String userId);
}
