package com.mpage.msvc.infocars.domain.repositories;

import com.mpage.msvc.infocars.domain.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity,Long> {

}
