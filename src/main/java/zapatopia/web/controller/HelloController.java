package zapatopia.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class HelloController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(
            value = "Permite obtener todos los clientes",
            response = ResponseEntity.class,
            produces = "application/json",
            tags = {
                    "clientes"
            })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = String.class),
            @ApiResponse(code = 400, message = "Datos de entrada incorrecto", response = String.class)
    })
    public ResponseEntity<String> clientesClientesGet() {
        ResponseEntity<String> response = null;
        String saludo = "Hola mundo";

        response = new ResponseEntity<>(saludo, HttpStatus.OK);

        return response;
    }

}
