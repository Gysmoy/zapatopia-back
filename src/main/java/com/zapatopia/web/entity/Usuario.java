package com.zapatopia.web.entity;

import com.zapatopia.web.entity.Persona;
import com.zapatopia.web.entity.Rol;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "correo_recuperacion")
    private String correoRecuperacion;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}