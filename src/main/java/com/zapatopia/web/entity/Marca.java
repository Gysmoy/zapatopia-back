package com.zapatopia.web.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "MARCAS")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "descripcion")
    private String descripcion;
}