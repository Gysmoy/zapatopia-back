package com.zapatopia.web.entity;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "almacen_id")
    private Integer almacenId;

    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;
    
}