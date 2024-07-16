package zapatopia.web.services;

import zapatopia.web.dto.VentaDto;

import java.util.List;

public interface VentaService {

    VentaDto generarVenta(VentaDto venta);
    List<VentaDto> listarVentas();
    boolean actualizarEstado(Long idVenta, Long idEstado);
}
