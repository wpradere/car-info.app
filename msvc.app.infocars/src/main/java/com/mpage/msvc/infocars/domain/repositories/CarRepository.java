package com.mpage.msvc.infocars.domain.repositories;

import com.mpage.msvc.infocars.domain.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity,Long> {

    List<CarEntity> findAllByUser_Id(Long userId);

    CarEntity findAllByPlaca(String placa);
    Optional<CarEntity> findByPlaca(String placa);
}
