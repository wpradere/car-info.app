package com.mpage.msvc.infocars.api.controllers;


import com.mpage.msvc.infocars.api.models.request.CarDeleteRequest;
import com.mpage.msvc.infocars.api.models.request.CarRegisterRequest;
import com.mpage.msvc.infocars.api.models.request.CarUpdateRequest;
import com.mpage.msvc.infocars.api.models.response.CarDeleteResponse;
import com.mpage.msvc.infocars.api.models.response.CarRegisterResponse;
import com.mpage.msvc.infocars.api.models.response.CarUpdateResponse;
import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.infrastructure.services.CarDeleteService;
import com.mpage.msvc.infocars.infrastructure.services.CarListService;
import com.mpage.msvc.infocars.infrastructure.services.CarRegisterService;
import com.mpage.msvc.infocars.infrastructure.services.CarUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarRegisterService carRegisterService;
    private final CarListService carListService;
    private final CarUpdateService carUpdateService;
    private final CarDeleteService carDeleteService;

    @PostMapping("/insert")
    public CarRegisterResponse register(

            @RequestBody final CarRegisterRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        return carRegisterService.insertCar(request, authentication);
    }

    @GetMapping("/all")
    public List<CarEntity> getAllCars(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication)
    {
        return carListService.list(authentication);
    }

    @PutMapping("/update")
    public ResponseEntity<CarUpdateResponse> updateCar(@RequestBody CarUpdateRequest request){
        return ResponseEntity.ok(carUpdateService.updateTask(request));
    }

    @DeleteMapping("deleteCar/{id}")
    public ResponseEntity<CarDeleteResponse> deleteCar (@PathVariable String id) throws Throwable {
        CarDeleteRequest request = new CarDeleteRequest();
        request.setPlaca(id);
        return ResponseEntity.ok(carDeleteService.deleteCar(request));
    }
}
