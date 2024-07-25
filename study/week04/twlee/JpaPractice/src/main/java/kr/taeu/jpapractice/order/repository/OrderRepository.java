package kr.taeu.jpapractice.order.repository;

import kr.taeu.jpapractice.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
