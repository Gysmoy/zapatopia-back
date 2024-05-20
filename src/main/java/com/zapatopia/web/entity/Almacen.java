package com.zapatopia.web.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "ALMACENES")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "almacen")
    private String almacen;
    @Column(name = "direccion")
    private String direccion;
}