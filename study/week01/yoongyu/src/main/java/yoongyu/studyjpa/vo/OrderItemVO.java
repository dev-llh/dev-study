package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="item")
public class OrderItemVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id", nullable = false, columnDefinition = "long")
    private long orderItemId;

    @Column(name="price", nullable = false, columnDefinition = "int")
    private int price;

    @Column(name="count", nullable = false, columnDefinition = "int")
    private int count;

    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private long orderId;

    @JoinColumn(name="item_id", referencedColumnName = "item_id")
    private long itemId;

    private String useYn;

}
