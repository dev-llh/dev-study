package yoongyu.studyjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoongyu.studyjpa.vo.OrderContentVO;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContentVO, Long> {



}
