package com.mpage.msvc.infocars.api.controllers;


import com.mpage.msvc.infocars.api.models.request.LoginRequest;
import com.mpage.msvc.infocars.api.models.request.RegisterRequest;
import com.mpage.msvc.infocars.api.models.request.TokenResponse;
import com.mpage.msvc.infocars.infrastructure.services.UserLoginService;
import com.mpage.msvc.infocars.infrastructure.services.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> registrer(@RequestBody final RegisterRequest request) {
    final TokenResponse token = userRegisterService.register(request);
    return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest request) {
        final TokenResponse response = userLoginService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return  userRegisterService.refreshToken(authentication);
    }
}
