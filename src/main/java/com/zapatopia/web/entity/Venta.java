package com.zapatopia.web.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "VENTAS")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "estado_id")
    private Integer estadoId;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
}