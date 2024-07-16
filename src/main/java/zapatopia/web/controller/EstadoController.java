package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zapatopia.web.dto.EstadoDto;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.EstadoService;

import java.util.List;

@RestController
@RequestMapping("/api/states")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/")
    public ResponseEntity<MainResponse> listarEstados() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            List<EstadoDto> estados = estadoService.listarEstados();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(estados);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
