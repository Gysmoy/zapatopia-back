package zapatopia.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.jpa.CategoriaJpa;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerCategoriasGet() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            List<CategoriaJpa> categorias = categoriaService.obtenerCategorias();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(categorias);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{categoriaid}", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerCategoriaByIdGet(
            @PathVariable("categoriaid") long categoriaid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            CategoriaJpa categoria = categoriaService.obtenerCategoria(categoriaid);
            if (categoria == null) {
                response.setStatus(404);
                response.setMessage("No se ha encontrado la categoria");
                entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatus(200);
                response.setMessage("Operacion correcta");
                response.setData(categoria);
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
    public ResponseEntity<MainResponse> crearCategoriaPost(
            @RequestBody CategoriaJpa categoria
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            CategoriaJpa categoriaGuardada = categoriaService.guardarCategoria(categoria);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(categoriaGuardada);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{categoriaid}", method = RequestMethod.DELETE)
    public ResponseEntity<MainResponse> eliminarCategoriaDelete(
            @PathVariable("categoriaid") long categoriaid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            categoriaService.eliminarCategoria(categoriaid);
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
