package zapatopia.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    @ApiOperation(
            value = "Permite obtener todas las marcas",
            response = ResponseEntity.class,
            produces = "application/json",
            tags = {
                    "brands"
            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = MainResponse.class),
            @ApiResponse(code = 400, message = "Datos de entrada incorrecto", response = MainResponse.class)
    })
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
    @ApiOperation(
            value = "Permite obtener una marca por id",
            response = ResponseEntity.class,
            produces = "application/json",
            tags = {
                    "clientes"
            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = MainResponse.class),
            @ApiResponse(code = 400, message = "Datos de entrada incorrecto", response = MainResponse.class)
    })
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

}
