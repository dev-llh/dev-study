package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="order_content")
public class OrderContentVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id", columnDefinition = "long")
    private long orderId;

    @Column(name="order_type", columnDefinition = "varchar(6)")
    private String orderType;

    @Column(name="order_status", columnDefinition = "varchar(6)")
    private String orderStatus;

    @Column(name="order_dtt", columnDefinition = "datetime")
    private LocalDateTime orderDtt; // ISO- "2024-08-24T09:00:00"

    @Column(name="detail", columnDefinition = "varchar(500)")
    private String detail;

    @JoinColumn(name="store_id", columnDefinition = "int")
    private Long storeId;

    @Column(name="use_yn")
    private String useYn;

}
