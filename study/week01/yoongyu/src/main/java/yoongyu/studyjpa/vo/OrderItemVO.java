package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="order_item")
public class OrderItemVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id", columnDefinition = "long")
    private Long orderItemId;

    @Column(name="price", columnDefinition = "int")
    private Integer price;

    @Column(name="count", columnDefinition = "int")
    private Integer count;

    @JoinColumn(name="order_id", columnDefinition = "int")
    private Long orderId;

    @JoinColumn(name="item_id", columnDefinition = "int")
    private Long itemId;

    @Column(name="use_yn", columnDefinition = "varchar(1)")
    private String useYn;

}
