# Spring Security
<hr/>

![img.png](img.png)

### 기본 용어
> * 접근 주체(Principal): 보호된 대상에 접근하는 유저
> * 인증(Authentication): 유저 아이디와 비밀번호를 이용하여 로그인하는 과정
> * 인가(Authorization): '권한부여'나 '허가'와 같은 의미로 사용
> * 권한: 인가 과정에서 해당 리소스에 대한 제한된 최소한의 권한을 가졌는지 확인

<br/>
<br/>

### 선 총평 : 보안 프레임워크
로그인 기능과 인가에 대한 기능을 공통적인 뼈대를 제공하고, 개발자가  
각자 상황에 맞게 자신에게 필요한 부분을 커스터마이징 하도록 제공하는 것 같음
시큐리티를 활성화 시, Filter 에서 스프링 시큐리티가 작동된다.
즉, 시큐리티 설정에서 오류나면 컨트롤러까지 안온다.

<br/>
<br/>

### ROLE
먼저 문자열기반의 룰이 필요함.  
이 문자열 기반으로 인가를 진행.  
보통 유지보수의 편의성을 위해 Enum을 사용하는 것 같음
``` java
@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "회원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;
}
```

<br/>
<br/>

### Filter Chain
시큐리티 기본 설정
``` java
        http
                .csrf(AbstractHttpConfigurer::disable) //csrf Oauth2, jwt토큰 사용 시 불필요함
                .headers((headerConfig) -> headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers(PathRequest.toH2Console()).permitAll() //h2 콘솔 사용 6.0.0 이상부터 설정해야 h2-console에 접근이 가능
                                .requestMatchers("/", "/main", "/login/**").permitAll() // 모두 허용
                                .requestMatchers("/info/**").hasRole(Role.USER.name()) // user만 허용
                                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name()) //admin만 허용
                                .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig.authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(accessDeniedHandler)
                )
                .formLogin((formLogin) -> //로그인 관련
                        formLogin
                                .loginPage("/login") //로그인페이지
                                .usernameParameter("username") //username = 아이디 의 파라미터명
                                .passwordParameter("password")//password = 비밀번호 의 파라미터명
                                .loginProcessingUrl("/login/process") //로그인 프로세스가 진행되는 엔드포인트 따로 구성하면 안됨
                                .defaultSuccessUrl("/main", true) //로그인 성공 시 리다이렉트 되는 uri
                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/main") //로그인 실패 시 리다이렉트 되는 uri
                )
                .userDetailsService(userService); // 로그인에 사용되는 서비스
```

<br/>
<br/>

### userDetailService
``` java
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("유저가 없음");
        }
        //암호화 미진행 시, 패스워드에 {noop} 안붙이면 에러
        //여기의 User는 org.springframework.security.core.userdetails.User 임 
        //UserDetails 인터페이스를 상속받아 추가할 것 추가하는 방식으로 직접 구현해도 됨
        return User.builder().username(userEntity.getUsername()).password("{noop}"+userEntity.getPassword()).roles(userEntity.getRole().name()).build();
    }

    public UserEntity saveUser(UserEntity joinUser) throws Exception {

        return userRepository.save(joinUser);
    }
}
```
UserDetailService 인터페이스를 구현하여  
loadUserByUsername 를 재정의한 클래스가 필요함 여기서는 회원가입을 위해 saveUser도 추가하였음  
Together도입 시 이부분에 SSO인증을 붙일까 생각 중..

<br/>
<br/>

### @AuthenticationPrincipal
``` java
    @GetMapping("/employee")
    public ModelAndView employee(@AuthenticationPrincipal User user) throws Exception {
        ModelAndView mav = new ModelAndView("info/employee");
        mav.addObject("userInfo", user);

        return mav;
    }
```
현재 로그인 된 유저정보 획득은 @AuthenticationPrincipal 을 통해 접근 가능

<br/>
<br/>

### 좀 더 알아봐야 할 것들
기본적으로 스프링 시큐리티는 세션에 인증정보를 저장하기에,   
이중화 서버를 기본 구성으로 가져가는 상황에서는
세션클러스터링, 토큰 기반 인증을 고려 해야 할 듯

<br/>
<br/>
<br/>
<br/>