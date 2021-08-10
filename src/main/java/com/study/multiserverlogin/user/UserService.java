package com.study.multiserverlogin.user;

import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public Boolean userSave(UserValue userValue) {
        //아이디 중복이면 false return
        if (userEntityRepository.existsByUserId(userValue.getUserId())) {
            return false;
        }
        userEntityRepository.save(UserEntity.create(userValue));
        //성공시 true return
        return true;
    }


}
