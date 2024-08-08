package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "store")
@Table(name="store")
public class StoreVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id", columnDefinition = "long")
    private Long storeId;

    @Column(name="store_nm", columnDefinition = "varchar(50)")
    private String storeNm;

    @Column(name="address", columnDefinition = "varchar(200)")
    private String address;

    @Column(name="president", columnDefinition = "varchar(50)")
    private String president;

    @Column(name="store_status", columnDefinition = "varchar(6)")
    private String storeStatus;

    @Column(name="use_yn", columnDefinition = "varchar(1)")
    private String useYn;

//    @OneToMany(mappedBy = "storeVO")
//    private List<CategoryVO> categoryList = new ArrayList<>();

}
