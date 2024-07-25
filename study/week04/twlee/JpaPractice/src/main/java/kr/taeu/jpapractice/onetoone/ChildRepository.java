package kr.taeu.jpapractice.onetoone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChildRepository extends JpaRepository<ChildEntity, Long> {
}
