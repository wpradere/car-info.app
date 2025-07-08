package com.mpage.msvc.infocars.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "cars_inf")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private int year;
    private String placa;
    private String color;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_user_id")
    @JsonBackReference
    public UserEntity user;
}
