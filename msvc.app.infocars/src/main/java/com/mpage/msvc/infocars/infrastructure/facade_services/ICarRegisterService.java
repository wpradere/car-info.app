package com.mpage.msvc.infocars.infrastructure.facade_services;

import com.mpage.msvc.infocars.api.models.request.CarRegisterRequest;
import com.mpage.msvc.infocars.api.models.response.CarRegisterResponse;
import com.mpage.msvc.infocars.api.models.response.ResponseInserUser;

public interface ICarRegisterService extends CrudService<CarRegisterRequest, ResponseInserUser,Long>{

    CarRegisterResponse insertCar(CarRegisterRequest request,String authorization);
}
