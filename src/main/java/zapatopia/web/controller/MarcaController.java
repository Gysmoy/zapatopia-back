package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.MarcaService;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerMarcasGet() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            List<MarcaJpa> marcas = marcaService.obtenerMarcas();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(marcas);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{marcaid}", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerMarcaByIdGet(
            @PathVariable("marcaid") long marcaid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            MarcaJpa marca = marcaService.obtenerMarca(marcaid);
            if (marca == null) {
                response.setStatus(404);
                response.setMessage("No se ha encontrado la marca");
                entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatus(200);
                response.setMessage("Operacion correcta");
                response.setData(marca);
                entity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MainResponse> guardarMarcaPost(
            @RequestBody MarcaJpa marca
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            MarcaJpa marcaGuardada = marcaService.guardarMarca(marca);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(marcaGuardada);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{marcaid}", method = RequestMethod.DELETE)
    public ResponseEntity<MainResponse> eliminarMarcaDelete(
            @PathVariable("marcaid") long marcaid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            marcaService.eliminarMarca(marcaid);

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
