package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="category")
@Table(name="category")
public class CategoryVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id", columnDefinition = "long")
    private Long categoryId;

    @Column(name="category_nm", columnDefinition = "varchar(100)")
    private String categoryNm;

    @ManyToOne
    @JoinColumn(name="store_id")
    private StoreVO storeVO;

    @Column(name="use_yn", columnDefinition = "varchar(1)")
    private String useYn;

}
