package yoongyu.studyjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoongyu.studyjpa.vo.OrderVO;

@Repository
public interface OrderRepository extends JpaRepository<OrderVO, Long> {



}
