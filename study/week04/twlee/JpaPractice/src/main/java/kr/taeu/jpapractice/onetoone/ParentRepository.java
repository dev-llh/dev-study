package kr.taeu.jpapractice.onetoone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<ParentEntity, Long> {
}
