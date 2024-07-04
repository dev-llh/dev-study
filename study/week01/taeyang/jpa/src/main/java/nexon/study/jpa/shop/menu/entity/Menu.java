package nexon.study.jpa.shop.menu.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nexon.study.jpa.shop.entity.Shop;
import nexon.study.jpa.shop.entity.ShopCategory;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu")
@Entity
public class Menu {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_category")
    private ShopCategory shopCategory;


    @Column(name = "date")
    private LocalDateTime date;
}
