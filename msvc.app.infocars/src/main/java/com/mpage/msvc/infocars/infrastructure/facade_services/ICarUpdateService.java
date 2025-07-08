package com.mpage.msvc.infocars.infrastructure.facade_services;

import com.mpage.msvc.infocars.api.models.request.CarUpdateRequest;
import com.mpage.msvc.infocars.api.models.response.CarUpdateResponse;

public interface ICarUpdateService extends CrudService<CarUpdateRequest, CarUpdateResponse,Long>{

    CarUpdateResponse updateTask(CarUpdateRequest request);
}
