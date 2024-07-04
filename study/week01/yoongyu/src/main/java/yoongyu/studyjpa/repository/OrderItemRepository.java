package yoongyu.studyjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoongyu.studyjpa.vo.OrderItemVO;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemVO, Long> {
}
