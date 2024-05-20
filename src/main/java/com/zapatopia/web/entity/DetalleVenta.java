package com.zapatopia.web.entity;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "DETALLE_VENTAS")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "venta_id")
    private Integer ventaId;

    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "cantidad")
    private Integer cantidad;
 
    @Column(name = "precio")
    private BigDecimal precio;
}