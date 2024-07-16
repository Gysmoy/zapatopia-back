package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.dto.OfertaDto;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.OfertaService;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/")
    public ResponseEntity<MainResponse> listarOfertas() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            List<OfertaDto> ofertas = ofertaService.listarOfertas();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(ofertas);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/")
    public ResponseEntity<MainResponse> crearOferta(@RequestBody OfertaDto ofertaDto) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            OfertaDto ofertaCreada = ofertaService.crearOferta(ofertaDto);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(ofertaCreada);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @DeleteMapping("/{ofertaId}")
    public ResponseEntity<MainResponse> eliminarOferta(@PathVariable("ofertaId") long ofertaId) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            ofertaService.eliminarOferta(ofertaId);
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @PatchMapping("/send/email/{ofertaId}")
    public ResponseEntity<MainResponse> enviarCorreoMasivo(@PathVariable("ofertaId") long ofertaId) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            ofertaService.enviarCorreoMasivo(ofertaId);
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
