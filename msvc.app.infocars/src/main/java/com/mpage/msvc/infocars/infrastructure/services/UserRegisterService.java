package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.api.models.request.LoginRequest;
import com.mpage.msvc.infocars.api.models.request.RegisterRequest;
import com.mpage.msvc.infocars.api.models.request.TokenResponse;
import com.mpage.msvc.infocars.domain.entity.Token;
import com.mpage.msvc.infocars.domain.entity.UserEntity;
import com.mpage.msvc.infocars.domain.repositories.TokenRepository;
import com.mpage.msvc.infocars.domain.repositories.UserRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.IUserRegisterService;
import com.mpage.msvc.infocars.util.exceptions.IdNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
@RequiredArgsConstructor
public class UserRegisterService implements IUserRegisterService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;



   @Override
   public TokenResponse register(RegisterRequest requestInsertUser) {

            var userPersist = UserEntity.builder()
                    .username(requestInsertUser.username())
                    .password(passwordEncoder.encode(requestInsertUser.password()))
                    .email(requestInsertUser.email())
                    .build();

            var savedUser = userRepository.save(userPersist);
            var jwtToken = jwtService.generateToken(userPersist);
            var refreshToken = jwtService.generateRefreshToken(userPersist);
            saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
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

    public TokenResponse refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        //se optiene el token sin el Bearer
        final String refreshToken = authentication.substring(7);
        //se extrae el usuario
        final String userName = jwtService.extractUsername(refreshToken);
        if (userName == null) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        final UserEntity user = this.userRepository.findByUsername(userName).orElseThrow(() -> new IdNotFoundExceptions("Invalid user"));
        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        final String accessToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }




}
