package kr.taeu.jpapractice.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;

    private String name;

    private BigDecimal price;

    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isSoldOut;

    @PrePersist
    public void prePersist() {
        System.out.println("ProductEntity.prePersist id=" + productId);
    }

    @PostPersist
    public void postPersist() {
        System.out.println("ProductEntity.postPersist id=" + productId);
    }

    @PostLoad
    public void postLoad() {
        System.out.println("ProductEntity.postLoad");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("ProductEntity.preRemove");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("ProductEntity.postRemove");
    }

    @Builder
    public ProductEntity(Long productId, String name, BigDecimal price, Boolean isSoldOut) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.isSoldOut = isSoldOut;
    }
}
