# multi-server-login-study

# 프로젝트 명세서 - session
|Method|URI|Description|
|------|---|---|
|POST|/user/join|회원가입|
|POST|/user/login|로그인|
|GET|/|home|
|GET|/login-check|로그인유무확인|

# 상세

### 요청양식

- /user/join 
 POST/JSON  >>

  {

  "userId" : "test-user",

  "password" : "1234!"

  }
- /user/login
  {

  "userId" : "test-user",

  "password" : "1234!"

  }