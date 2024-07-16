package zapatopia.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfertaDto {
    private long id;
    private ProductoVentaDto producto;
    private Integer cantidadEntregar;
    private Integer cantidadComprar;
    private boolean envioAutomatico;
    private LocalDateTime fechaModificacion;
}
