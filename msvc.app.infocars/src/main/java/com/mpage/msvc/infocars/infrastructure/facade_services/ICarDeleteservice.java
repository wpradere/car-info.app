package com.mpage.msvc.infocars.infrastructure.facade_services;

import com.mpage.msvc.infocars.api.models.request.CarDeleteRequest;
import com.mpage.msvc.infocars.api.models.response.CarDeleteResponse;

public interface ICarDeleteservice extends CrudService<CarDeleteRequest, CarDeleteResponse,Long>{

    CarDeleteResponse deleteCar(CarDeleteRequest request) throws Throwable;
}
