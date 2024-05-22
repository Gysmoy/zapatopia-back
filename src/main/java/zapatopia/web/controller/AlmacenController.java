package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.jpa.AlmacenJpa;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.AlmacenService;
import zapatopia.web.services.MarcaService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerAlmacenesGet() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            List<AlmacenJpa> almacenes = almacenService.obtenerAlmacenes();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(almacenes);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{almacenid}", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerAlmacenByIdGet(
            @PathVariable("almacenid") long almacenid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            AlmacenJpa almacen = almacenService.obtenerAlmacen(almacenid);
            if (almacen == null) {
                response.setStatus(404);
                response.setMessage("No se ha encontrado el almacen");
                entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatus(200);
                response.setMessage("Operacion correcta");
                response.setData(almacen);
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
    public ResponseEntity<MainResponse> crearAlmacenPost(
            @RequestBody AlmacenJpa almacen
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            AlmacenJpa almacenGuardado = almacenService.guardarAlmacen(almacen);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(almacenGuardado);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{almacenid}", method = RequestMethod.DELETE)
    public ResponseEntity<MainResponse> eliminarAlmacenDelete(
            @PathVariable("almacenid") long almacenid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            almacenService.eliminarAlmacen(almacenid);
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
