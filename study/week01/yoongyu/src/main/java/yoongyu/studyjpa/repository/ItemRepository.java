package yoongyu.studyjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoongyu.studyjpa.vo.ItemVO;

@Repository
public interface ItemRepository extends JpaRepository<ItemVO, Long> {
}
