package kr.taeu.jpapractice.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;
}
