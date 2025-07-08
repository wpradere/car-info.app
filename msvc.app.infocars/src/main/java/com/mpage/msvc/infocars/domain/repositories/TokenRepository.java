package com.mpage.msvc.infocars.domain.repositories;

import com.mpage.msvc.infocars.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {


    List<Token> findByUser_IdAndExpiredFalseAndRevokedFalse(Long userId);

    Optional<Token> findByToken(String token);
}
