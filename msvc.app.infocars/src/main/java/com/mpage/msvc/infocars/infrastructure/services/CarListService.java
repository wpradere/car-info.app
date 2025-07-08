package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.domain.repositories.CarRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.ICarListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CarListService implements ICarListService {

    private final CarRepository carRepository;

    @Override
    public List<CarEntity> list() {
       /* Iterable<CarEntity> carEntities = carRepository.findAll();
        List<CarEntity> carList = StreamSupport.stream(carEntities.spliterator(), false)
                .toList();*/

        return carRepository.findAll();
    }
}
