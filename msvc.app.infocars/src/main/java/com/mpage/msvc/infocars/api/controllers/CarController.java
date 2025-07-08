package com.mpage.msvc.infocars.api.controllers;


import com.mpage.msvc.infocars.api.models.request.CarRegisterRequest;
import com.mpage.msvc.infocars.api.models.response.CarRegisterResponse;
import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.infrastructure.services.CarListService;
import com.mpage.msvc.infocars.infrastructure.services.CarRegisterService;
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

    @PostMapping("/insert")
    public CarRegisterResponse register(

            @RequestBody final CarRegisterRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication) {
        return carRegisterService.insertCar(request, authentication);
    }

    @GetMapping("/all")
    public List<CarEntity> getAllCars() {
        return carListService.list();
    }

}
