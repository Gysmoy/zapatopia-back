package zapatopia.web.dto;

import lombok.Data;

@Data
public class DetalleVentaDto {

    private long id;
    private VentaDto venta;
    private ProductoVentaDto producto;
    private Integer cantidad;
    private Double precio;

}
