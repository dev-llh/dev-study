package kr.taeu.jpapractice.user.model;

import jakarta.persistence.*;
import kr.taeu.jpapractice.team.model.TeamEntity;
import lombok.*;

@Builder
@Entity
@Table
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQueries({
        @NamedQuery(
                name="UserEntity.findByName2",
                query="select u from UserEntity u where u.name = :name"
        )
})
@NamedEntityGraph(name = "UserEntity.withTeam", attributeNodes = {
        @NamedAttributeNode("team")
})
public class UserEntity {

    @Id
    private String userId;

    private String name;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team")
    private TeamEntity team;

    public void setTeam(TeamEntity team) {
        this.team = team;

        if (!team.getUsers().contains(this)) {
           team.getUsers().add(this);
        }
    }
}
