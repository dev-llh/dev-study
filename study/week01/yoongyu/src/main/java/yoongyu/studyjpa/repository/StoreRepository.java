package yoongyu.studyjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoongyu.studyjpa.vo.StoreVO;

@Repository
public interface StoreRepository extends JpaRepository<StoreVO, Long> {
}
