package kr.taeu.jpapractice.user.repository;

import kr.taeu.jpapractice.user.model.UserEntity;

import java.util.List;

public interface UserRepositorySupporter {

    List<UserEntity> findByNameCustom(String name);
}
