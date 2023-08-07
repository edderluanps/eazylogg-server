package com.eazylogg.backend.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.eazylogg.backend.models.dto.EmailDTO;
import com.eazylogg.backend.security.JWTUtil;
import com.eazylogg.backend.security.UserSS;
import com.eazylogg.backend.services.AuthService;
import com.eazylogg.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/refresh_token")
    public void refreshToken(HttpServletResponse httpServletResponse) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
    }

    @GetMapping(value = "/forgot")
    public void forgot(@Valid @RequestBody EmailDTO emailDto) {
        authService.sendNewPassword(emailDto.getEmail());
    }
}