package kr.taeu.jpapractice.team.model;

import jakarta.persistence.*;
import kr.taeu.jpapractice.user.model.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table
@Getter
@ToString(exclude = "users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long teamId;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "team")
    private List<UserEntity> users = new ArrayList<>();

    public void addUser(UserEntity user) {
        users.add(user);
        if (user.getTeam() == null) {
            user.setTeam(this);
        }
    }
}
