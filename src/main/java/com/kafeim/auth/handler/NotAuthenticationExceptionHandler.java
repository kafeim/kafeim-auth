package com.kafeim.auth.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liubo
 * @Project Name: kafeim-auth
 * @File Name: NotAuthenticationExceptionHandler
 * @Createtime: 2022-06-24 17:44
 * @Description: 异常处理器。
 */
public class NotAuthenticationExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED , authException.getMessage());

    }
}
