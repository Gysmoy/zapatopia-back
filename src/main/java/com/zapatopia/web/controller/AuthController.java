package com.zapatopia.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.List;

@RestController
@RequestMapping
public class ClientesController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    @RequestMapping(value = "/clientes/clientes", method = RequestMethod.GET)
    @ApiOperation(value = "Permite obtener todos los clientes", response = ResponseEntity.class, produces = "application/json", tags = {
            "clientes"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = Clientes.class),
            @ApiResponse(code = 400, message = "Datos de entrada incorrecto", response = Clientes.class)})
    public ResponseEntity<Clientes> clientesClientesGet() {
        ResponseEntity<Clientes> response = null;
        Clientes clientes = null;

        try {
            clientes = clientesService.obtenerClientes();
            if (configProperties.getCodigo200().equalsIgnoreCase(clientes.getResultado().getCodigoRespuesta())) {
                response = new ResponseEntity<>(clientes, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(clientes, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(clientes, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @RequestMapping(value = "/clientes/paginado", method = RequestMethod.POST)
    @ApiOperation(value = "Permite obtener todos los clientes", response = ResponseEntity.class, produces = "application/json", tags = {
            "clientes"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = Clientes.class),
            @ApiResponse(code = 400, message = "Datos de entrada incorrecto", response = Clientes.class)})
    public ResponseEntity<Clientes> clientesPaginado(@RequestBody Filter filter) {
        ResponseEntity<Clientes> response = null;
        Clientes clientes = null;

        try {
            clientes = clientesService.obtenerClientesPaginado(filter);
            if (configProperties.getCodigo200().equalsIgnoreCase(clientes.getResultado().getCodigoRespuesta())) {
                response = new ResponseEntity<>(clientes, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(clientes, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(clientes, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @RequestMapping(value = "/clientes/estado/{estado}", method = RequestMethod.PATCH)
    @ApiOperation(value = "Permite cambiar el estado/eliminar todos los clientes seleccionados", response = ResponseEntity.class, produces = "application/json", tags = {
            "clientes"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = Clientes.class),
            @ApiResponse(code = 400, message = "Datos de entrada incorrecto", response = Clientes.class)})
    public ResponseEntity<Resultado> cambiarEstadoMasivo(
            @PathVariable("estado") String estado,
            @RequestBody List<String> ids
    ) {
        ResponseEntity<Resultado> response = null;
        Resultado resultado = null;
        try {
            resultado = clientesService.cambiarEstadoMasivo(estado, ids);
            if (configProperties.getCodigo200().equalsIgnoreCase(resultado.getCodigoRespuesta())) {
                response = new ResponseEntity<>(resultado, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        }

        return response;
    }


    @RequestMapping(value = "/clientes/clientes/{clienteid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Permite borrar un registro", response = ResponseEntity.class, produces = "application/json", tags = {
            "personas"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta", response = Resultado.class),
            @ApiResponse(code = 400, message = "Error en los datos de entrada", response = Resultado.class)})
    public ResponseEntity<Resultado> clientesClienteIdDelete(@PathVariable("clienteid") String clienteid) {
        ResponseEntity<Resultado> response = null;
        Resultado resultado = null;
        try {
            resultado = clientesService.eliminarClienteId(clienteid);
            if (configProperties.getCodigo200().equalsIgnoreCase(resultado.getCodigoRespuesta())) {
                response = new ResponseEntity<>(resultado, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    */
}