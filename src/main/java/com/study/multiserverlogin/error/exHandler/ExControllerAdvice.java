package com.study.multiserverlogin.error.exHandler;

import com.study.multiserverlogin.error.exception.LoginException;
import com.study.multiserverlogin.response.BaseResponse;
import com.study.multiserverlogin.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {


    /**
     * error 가 터질 경우 원하는 방식으로 return 하기 위해서 지금 방식 사용
     * 특정 클래스를 지정안했을때는 전체적용, default message 느낌
     * Base 적인거 몇개만 지정 해 두고 추가로 생길때마다 판단해서 추가.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[illegalExHandler] ex", e);
        return ErrorResult.of(ErrorStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<? extends BaseResponse> loginExHandler(LoginException e) {
        log.error("[loginExHandler] ex =", e);
//        return ErrorResult.of(ErrorStatus.LOGIN_EXCEPTION, e.getMessage());
        return ResponseEntity.badRequest().body(LoginResponse.fail("로그인이 필요합니다.!!", e.getMessage()));
    }


    /**
     * error status 모음
     */
    private static class ErrorStatus {
        private static final String BAD_REQUEST = "bad_request";
        private static final String LOGIN_EXCEPTION = "login_exception";
    }

}
