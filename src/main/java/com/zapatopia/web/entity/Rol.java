package com.zapatopia.web.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ROLES")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "rol")
    private String rol;
    
    @Column(name = "descripcion")
    private String descripcion;

    @Column(columnDefinition = "LONGTEXT")
    private String permisos;
}