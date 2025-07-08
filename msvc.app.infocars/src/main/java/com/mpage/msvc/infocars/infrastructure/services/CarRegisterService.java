package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.api.models.request.CarRegisterRequest;
import com.mpage.msvc.infocars.api.models.response.CarRegisterResponse;
import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.domain.repositories.CarRepository;
import com.mpage.msvc.infocars.domain.repositories.UserRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.ICarRegisterService;
import com.mpage.msvc.infocars.util.exceptions.IdNotFoundExceptions;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CarRegisterService implements ICarRegisterService {

    private final JwtService jwtService;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Override
    public CarRegisterResponse insertCar(CarRegisterRequest request, String autToken) {
        String refreshToken = autToken.substring(7);
        String user = jwtService.extractUsername(refreshToken);
        CarRegisterResponse response = new CarRegisterResponse();
        var userFind = userRepository.findByUsername(user).orElseThrow(() -> new IdNotFoundExceptions("Usuario no encontrado"));

        try {
            var carPersist = CarEntity.builder()
                    .marca(request.marca())
                    .modelo(request.modelo())
                    .year(request.year())
                    .placa(request.placa())
                    .color(request.color())
                    .user(userFind)
                    .build();
            var carPersistense = carRepository.save(carPersist);
            response.setMessage("Car registered successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error while inserting car");
        }


        return response;
    }
}
