package kr.taeu.jpapractice.user.repository;

import kr.taeu.jpapractice.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
