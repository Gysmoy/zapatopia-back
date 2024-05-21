package zapatopia.web.jpa;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "categoria_id")
    private Integer categoriaId;

    @Column(name = "marca_id")
    private Integer marcaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio_compra")
    private BigDecimal precioCompra;

    @Column(name = "talla")
    private Integer talla;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "genero")
    private Character genero;

    @Column(name = "stock_general")
    private Integer stockGeneral;
}