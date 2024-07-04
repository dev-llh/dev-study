package nexon.study.jpa.user.admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "amdin_user")
public class AdminUser {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "admin_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id")
    private String userId;

    @Column(name = "pw")
    private String password;

    @Column(name = "date")
    private LocalDateTime registerDate;
}
