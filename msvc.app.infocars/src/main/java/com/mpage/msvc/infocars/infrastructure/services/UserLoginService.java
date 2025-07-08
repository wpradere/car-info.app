package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.api.models.request.LoginRequest;
import com.mpage.msvc.infocars.api.models.request.TokenResponse;
import com.mpage.msvc.infocars.domain.entity.Token;
import com.mpage.msvc.infocars.domain.entity.UserEntity;
import com.mpage.msvc.infocars.domain.repositories.TokenRepository;
import com.mpage.msvc.infocars.domain.repositories.UserRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.IUserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserLoginService implements IUserLoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @Override
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        final UserEntity user = userRepository.findByUsername(request.username())
                .orElseThrow();
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }


    private void saveUserToken(UserEntity userEntity, String jwtToken) {
        var token =  Token.builder()
                .user(userEntity)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);

    }

    private void revokeAllUserTokens(final UserEntity user) {
        final List<Token> validUserTokens = tokenRepository.findByUser_IdAndExpiredFalseAndRevokedFalse(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }


}
