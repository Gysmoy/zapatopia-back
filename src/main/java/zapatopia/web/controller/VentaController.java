package zapatopia.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.dto.EstadoDto;
import zapatopia.web.dto.VentaDto;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Slf4j
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    public ResponseEntity<MainResponse> listarVentas() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            List<VentaDto> ventas = ventaService.listarVentas();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(ventas);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/")
    public ResponseEntity<MainResponse> generarVenta(@RequestBody VentaDto venta) {

        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            VentaDto ventaGenerada = ventaService.generarVenta(venta);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(ventaGenerada);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            log.error("Error generar venta: ", e);
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;

    }

    @PutMapping("/{idVenta}")
    public ResponseEntity<MainResponse> actualizarEstado(@PathVariable("idVenta") Long idVenta, @RequestBody VentaDto venta) {

        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            boolean update = ventaService.actualizarEstado(idVenta, venta.getEstado().getId());
            if(update) {
                response.setStatus(200);
                response.setMessage("Operacion correcta");
                entity = new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(204);
                response.setMessage("No se encontro registro");
                entity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }

        }catch (Exception e) {
            log.error("Error generar venta: ", e);
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;

    }
}
