package kr.taeu.jpapractice.product.repository;

import kr.taeu.jpapractice.product.model.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void findById_whenProductExists_thenReturnProduct() {
        ProductEntity saved = productRepository.save(ProductEntity.builder()
                .name("테스트상품")
                .price(new BigDecimal(100))
                .build());

        ProductEntity found = assertDoesNotThrow(() -> productRepository.findById(1L).get());

        assertEquals(saved, found);
        assertEquals(saved.getName(), found.getName());
    }
}