package com.mpage.msvc.infocars.infrastructure.facade_services;

import com.mpage.msvc.infocars.domain.entity.CarEntity;

import java.util.List;

public interface ICarListService {
    List<CarEntity>list(String authentication);
}
