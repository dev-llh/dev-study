package kr.taeu.jpapractice.order.model;

import jakarta.persistence.*;
import kr.taeu.jpapractice.user.model.UserEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table
@Getter
@ToString(exclude = "orderProductList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderId;

    private LocalDateTime orderDate;

    private BigDecimal totalPrice;

    private BigDecimal totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerId")
    private UserEntity buyer;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProductEntity> orderProductList = new ArrayList<>();

    public void addOrderProductEntity(OrderProductEntity orderProductEntity) {
        orderProductList.add(orderProductEntity);
        orderProductEntity.setOrder(this);
    }
}
