package nexon.study.jpa.user.general.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id")
    private String userId;

    @Column(name = "pw")
    private String password;

    @Column(name = "date")
    private LocalDateTime registerDate;
}
