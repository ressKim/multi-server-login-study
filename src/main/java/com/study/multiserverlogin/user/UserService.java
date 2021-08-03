package com.study.multiserverlogin.user;

import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final LoginSessionRepository loginSessionRepository;

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
