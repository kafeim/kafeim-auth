package com.kafeim.auth.config;

import com.kafeim.auth.handler.NotAuthenticationExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: liubo
 * @Project Name: kafeim-auth
 * @File Name: SpringSecurityConfig
 * @Createtime: 2022-06-24 17:29
 * @Description:
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 启动CORS 并且 禁用 CSRF
        http = http.cors().and().csrf().disable();

        // 将会话管理设置为无状态
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // 设置未经过授权的异常处理器
        http = http.exceptionHandling()
                .authenticationEntryPoint(new NotAuthenticationExceptionHandler()).and();

        http.authorizeRequests()
                // 设置放行 不需要身份验证的 API
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.POST , "/upload/*").permitAll()
                // 设置除了放行的API 其他API都需要经过身份验证
                .anyRequest().authenticated();



    }
}
