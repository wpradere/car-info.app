package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.api.models.request.CarUpdateRequest;
import com.mpage.msvc.infocars.api.models.response.CarUpdateResponse;
import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.domain.repositories.CarRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.ICarUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarUpdateService implements ICarUpdateService {
    private final CarRepository carRepository;

    @Override
    public CarUpdateResponse updateTask(CarUpdateRequest request) {
        var updateCar = carRepository.findAllByPlaca(request.placa());
        CarUpdateResponse updateResponse = new CarUpdateResponse();

        updateCar.setColor(request.color());
        updateCar.setMarca(request.marca());
        updateCar.setPlaca(updateCar.getPlaca());
        updateCar.setYear(request.year());
        updateCar.setModelo(request.modelo());
        carRepository.save(updateCar);
        return  this.entityToResponse(updateCar);
    }

    private CarUpdateResponse entityToResponse (CarEntity entity) {
        var response = new CarUpdateResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }

}
