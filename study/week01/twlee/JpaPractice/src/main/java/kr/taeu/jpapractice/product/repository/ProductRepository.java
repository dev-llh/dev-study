package kr.taeu.jpapractice.product.repository;

import kr.taeu.jpapractice.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
