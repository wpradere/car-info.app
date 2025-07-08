package com.mpage.msvc.infocars.infrastructure.services;

import com.mpage.msvc.infocars.domain.entity.CarEntity;
import com.mpage.msvc.infocars.domain.repositories.CarRepository;
import com.mpage.msvc.infocars.domain.repositories.UserRepository;
import com.mpage.msvc.infocars.infrastructure.facade_services.ICarListService;
import com.mpage.msvc.infocars.util.exceptions.IdNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CarListService implements ICarListService {

    private final CarRepository carRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public List<CarEntity> list(String autToken) {

        String refreshToken = autToken.substring(7);
        String user = jwtService.extractUsername(refreshToken);
        var userBd = userRepository.findByUsername(user).orElseThrow(() -> new IdNotFoundExceptions("Usuario no encontrado"));

        Iterable<CarEntity> carEntities = carRepository.findAllByUser_Id(userBd.getId());
        List<CarEntity> carList = StreamSupport.stream(carEntities.spliterator(), false)
                .toList();
        return carList;
    }


}
