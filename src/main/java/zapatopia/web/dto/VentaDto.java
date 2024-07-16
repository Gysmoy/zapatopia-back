package zapatopia.web.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VentaDto {

    private long id;
    private String codigoPedido;
    private ClienteDto cliente;
    private EstadoDto estado;
    private LocalDateTime fechaModificacion;
    private List<DetalleVentaDto> detalle;

}
