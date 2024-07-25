package kr.taeu.jpapractice.order.repository;

import kr.taeu.jpapractice.order.model.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
}
