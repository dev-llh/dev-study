package kr.taeu.jpapractice.order.model;

import jakarta.persistence.*;
import kr.taeu.jpapractice.product.model.ProductEntity;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Entity
@Table
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal productQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ProductEntity product;

    public void setOrder(OrderEntity order) {
        this.order = order;
        if (!order.getOrderProductList().contains(this)) {
            order.getOrderProductList().add(this);
        }
    }
}
