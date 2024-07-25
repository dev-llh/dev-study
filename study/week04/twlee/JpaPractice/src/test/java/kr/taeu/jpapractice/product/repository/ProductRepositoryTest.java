package kr.taeu.jpapractice.product.repository;

import jakarta.persistence.EntityManager;
import kr.taeu.jpapractice.JpaConfig;
import kr.taeu.jpapractice.product.model.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(JpaConfig.class)
@TestPropertySource(properties = {
        "logging.level.org.hibernate.orm.jdbc.bind=trace"
})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_whenProductExists_thenReturnProduct() {
        ProductEntity saved = productRepository.save(ProductEntity.builder()
                .name("테스트상품")
                .price(new BigDecimal(100))
                .isSoldOut(true)
                .build());

        List<ProductEntity> productEntities = assertDoesNotThrow(() -> productRepository.findAll());
        em.flush();
        ProductEntity productEntity = productEntities.get(0);
        // insert into product_entity (is_sold_out,name,price,product_id) values ("Y", "테스트상품", 100, 1)
        assertEquals(saved, productEntity);
        assertEquals(saved.getName(), productEntity.getName());
        assertEquals(saved.getIsSoldOut(), productEntity.getIsSoldOut());
    }
}