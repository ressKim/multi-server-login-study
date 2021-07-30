package com.study.multiserverlogin.user;

import com.study.multiserverlogin.domain.session.LoginSession;
import com.study.multiserverlogin.domain.session.LoginSessionRepository;
import com.study.multiserverlogin.domain.user.UserEntity;
import com.study.multiserverlogin.domain.user.UserEntityRepository;
import com.study.multiserverlogin.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final LoginSessionRepository loginSessionRepository;

    public void userSave(UserValue userValue) {
        userEntityRepository.save(UserEntity.create(userValue));
    }




}
