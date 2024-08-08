package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="item")
@Table(name="item")
public class ItemVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id", columnDefinition = "long")
    private Long itemId;

    @Column(name="item_nm", columnDefinition = "varchar(100)")
    private String itemNm;

    @Column(name="master_price", columnDefinition = "int")
    private Integer masterPrice;

    @ManyToOne
    @JoinColumn(name="store_id")
    private StoreVO storeVO;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryVO categoryVO;

    @Column(name="use_yn", columnDefinition = "varchar(1)")
    private String useYn;

    @Column(name="image", columnDefinition = "longblob")
    private byte[] image;

}
