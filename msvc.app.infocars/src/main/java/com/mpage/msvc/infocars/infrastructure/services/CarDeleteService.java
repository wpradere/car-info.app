package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.api.models.request.CarDeleteRequest;
import com.mpage.msvc.infocars.api.models.response.CarDeleteResponse;
import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.domain.repositories.CarRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.ICarDeleteservice;
import com.mpage.msvc.infocars.util.exceptions.IdNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarDeleteService implements ICarDeleteservice {
    private final CarRepository carRepository;

    @Override
    public CarDeleteResponse deleteCar(CarDeleteRequest request) throws Throwable {
        CarDeleteResponse response = new CarDeleteResponse();
        CarEntity carDelete = carRepository.findByPlaca(request.placa).orElseThrow(()->new IdNotFoundExceptions("Carro con placa " + request.placa + " no encontrado"));
          carRepository.delete(carDelete);
        return response;
    }
}
