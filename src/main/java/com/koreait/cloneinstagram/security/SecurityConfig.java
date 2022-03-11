package com.koreait.cloneinstagram.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOauth2UserService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //ajax-post 막힘을 풀어주기 위해
        
        http.headers().frameOptions().sameOrigin();

        http
            .authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**", "/user/signin", "/user/signup").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/user/signin")
                .usernameParameter("email")
                .passwordParameter("pw")
                .defaultSuccessUrl("/feed", true);

        http.oauth2Login()
                .loginPage("/user/signin")
                .defaultSuccessUrl("/feed", true)
                .failureUrl("/user/signin")
                .userInfoEndpoint() //OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당합니다.
                .userService(customOauth2UserService);

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/signout"))
                .logoutSuccessUrl("/user/signin")
                .invalidateHttpSession(true);
    }



}
