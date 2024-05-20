package com.zapatopia.web.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "descripcion")
    private String descripcion;
}