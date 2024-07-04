package kr.taeu.jpapractice.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    private String userId;

    private String name;

    @Builder
    public UserEntity(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
