package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="order")
public class OrderVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private String orderType;

    private String orderStatus;

    private String orderDtt;

    private String comment;

    private long storeId;

    private String useYn;

}
