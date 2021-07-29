package com.study.multiserverlogin.user;

import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public void userSave(UserValue userValue) {
        userEntityRepository.save(UserEntity.create(userValue));
    }
}
