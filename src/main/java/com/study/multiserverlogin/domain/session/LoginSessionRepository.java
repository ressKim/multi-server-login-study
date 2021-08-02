package com.study.multiserverlogin.domain.session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {

    LoginSession findBySessionKey(String sessionKey);
}
