package kr.taeu.jpapractice.onetoone;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @OneToOne(mappedBy = "parent", cascade = CascadeType.ALL)
    private ChildEntity child;
}
