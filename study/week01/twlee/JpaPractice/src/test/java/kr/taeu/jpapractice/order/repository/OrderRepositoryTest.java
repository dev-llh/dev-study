package kr.taeu.jpapractice.order.repository;

import kr.taeu.jpapractice.order.model.OrderEntity;
import kr.taeu.jpapractice.order.model.OrderProductEntity;
import kr.taeu.jpapractice.product.model.ProductEntity;
import kr.taeu.jpapractice.product.repository.ProductRepository;
import kr.taeu.jpapractice.user.model.UserEntity;
import kr.taeu.jpapractice.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
    "logging.level.org.hibernate.orm.jdbc.bind=trace"
})
class OrderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @BeforeEach
    public void setup() {
        userRepository.save(UserEntity.builder()
                .userId("twlee")
                .name("태우")
                .build());
        productRepository.save(ProductEntity.builder()
                .name("테스트상품1")
                .price(new BigDecimal(1000))
                .build());
        productRepository.save(ProductEntity.builder()
                .name("테스트상품2")
                .price(new BigDecimal(2000))
                .build());
    }

    @Test
    public void createOrder_whenValidInput_thenOrderReturn() {
        UserEntity buyer = userRepository.findById("twlee").get();
        List<ProductEntity> products = productRepository.findAll();

        List<OrderProductEntity> orderProductEntities = products.stream()
                .map(p -> OrderProductEntity.builder()
                        .product(p)
                        .productName(p.getName())
                        .productPrice(p.getPrice())
                        .productQuantity(new BigDecimal(100))
                        .build())
                .toList();

        OrderEntity orderEntity = OrderEntity.builder()
                .buyer(buyer)
                .orderDate(LocalDateTime.now())
                .totalPrice(orderProductEntities.stream()
                        .map(OrderProductEntity::getProductPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .totalQuantity(orderProductEntities.stream()
                        .map(OrderProductEntity::getProductQuantity)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();

        for (OrderProductEntity orderProductEntity : orderProductEntities) {
            orderEntity.addOrderProductEntity(orderProductEntity);
        }

        OrderEntity savedOrder = assertDoesNotThrow(() -> orderRepository.save(orderEntity));
        OrderEntity foundOrder = assertDoesNotThrow(() -> orderRepository.findById(1L).get());
        List<OrderProductEntity> foundOrderProducts = assertDoesNotThrow(() -> orderProductRepository.findAll());
        List<OrderProductEntity> orderProductsInOrder = foundOrder.getOrderProductList();

        assertEquals(savedOrder, foundOrder);
        assertIterableEquals(orderProductEntities, foundOrderProducts);
        assertIterableEquals(orderProductEntities, orderProductsInOrder);

        assertThat(orderProductsInOrder).allMatch(orderProductEntity -> orderProductEntity.getOrder() != null);
    }
}