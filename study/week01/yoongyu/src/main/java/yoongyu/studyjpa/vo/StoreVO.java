package yoongyu.studyjpa.vo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="store")
public class StoreVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeId;

    private String storeNm;

    private String address;

    private String president;

    private String storeStatus;

    private String useYn;

}
