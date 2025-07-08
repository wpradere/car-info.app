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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


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
        var carBd = carRepository.findByPlaca(request.placa());
        if (carBd.isPresent()) {
            response.setMessage("Car already exists");
        } else {
            Pattern placaPattern = Pattern.compile("^[a-zA-Z]{3}[0-9]{3}$");
            if (!placaPattern.matcher(request.placa()).matches()) {
                response.setMessage("Formato de placa inválido. Debe tener 3 letras seguidas de 3 números (ej. ABC123).");
                throw new IdNotFoundExceptions(
                        "Formato de placa inválido. Debe tener 3 letras seguidas de 3 números (ej. ABC123)."
                );
            }
            var userFind = userRepository.findByUsername(user).orElseThrow(() -> new IdNotFoundExceptions("Usuario no encontrado"));

            var carPersist = CarEntity.builder()
                    .marca(request.marca())
                    .modelo(request.modelo())
                    .year(request.year())
                    .placa(request.placa())
                    .color(request.color())
                    .user(userFind)
                    .build();
            carRepository.save(carPersist);
            response.setMessage("Car registered successfully");
        }
        return response;
    }
}
