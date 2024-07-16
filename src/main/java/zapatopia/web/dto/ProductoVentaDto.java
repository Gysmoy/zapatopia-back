package zapatopia.web.dto;

import lombok.Data;
import zapatopia.web.jpa.CategoriaJpa;
import zapatopia.web.jpa.MarcaJpa;

import java.time.LocalDateTime;

@Data
public class ProductoVentaDto {

    private long idStock;

    private long id;

    private CategoriaJpa categoria;

    private MarcaJpa marca;

    private String nombre;

    private String descripcion;

    private Double precioVenta;

    private Integer talla;

    private String color;

    private String genero;

    private String pathFoto;

}
