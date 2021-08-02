package com.study.multiserverlogin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    Boolean existsByUserId(String userId);

    Boolean existsByUserIdAndPassword(String userId, String password);
}
