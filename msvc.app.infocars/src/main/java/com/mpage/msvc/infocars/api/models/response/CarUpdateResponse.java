package com.mpage.msvc.infocars.api.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarUpdateResponse {
    public String marca;
    public String modelo;
    public Integer year;
    public String placa;
    public String color;
}
