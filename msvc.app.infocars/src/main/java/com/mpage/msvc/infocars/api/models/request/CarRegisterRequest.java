package com.mpage.msvc.infocars.api.models.request;

public record CarRegisterRequest(
        String marca,
        String modelo,
        Integer year,
        String placa,
        String color
) {
}
