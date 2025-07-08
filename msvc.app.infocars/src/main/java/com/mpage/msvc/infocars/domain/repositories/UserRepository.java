package com.mpage.msvc.infocars.domain.repositories;

import com.mpage.msvc.infocars.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername (String username);

}
