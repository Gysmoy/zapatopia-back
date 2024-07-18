package zapatopia.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import zapatopia.web.dto.ProductoVentaDto;
import zapatopia.web.jpa.ProductoJpa;
import zapatopia.web.models.MainResponse;
import zapatopia.web.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<MainResponse> listarProductos() {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            List<ProductoJpa> productos = productoService.listarProductos();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(productos);
            entity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @GetMapping("/sale")
    public ResponseEntity<MainResponse> listarProductosVenta(HttpServletRequest request) {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            List<ProductoVentaDto> productos = productoService.listarProductoVentas();
            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(productos);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{productoid}", method = RequestMethod.GET)
    public ResponseEntity<MainResponse> obtenerProductoByIdGet(
            @PathVariable("productoid") long productoid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            ProductoJpa producto = productoService.obtenerProducto(productoid);
            if (producto == null) {
                response.setStatus(404);
                response.setMessage("No se ha encontrado el producto");
                entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatus(200);
                response.setMessage("Operacion correcta");
                response.setData(producto);
                entity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/")
    public ResponseEntity<MainResponse> crearProducto(
            @RequestBody ProductoJpa productoJpa
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            ProductoJpa productoGuardado = productoService.crearProducto(productoJpa);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(productoGuardado);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<MainResponse> obtenerProductosPost(
            @RequestBody List<Long> listaId
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();

        try {
            List<ProductoJpa> productos = productoService.obtenerProductosPorListaId(listaId);

            response.setStatus(200);
            response.setMessage("Operacion correcta");
            response.setData(productos);
            entity = new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.getMessage());
            entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{productoid}", method = RequestMethod.DELETE)
    public ResponseEntity<MainResponse> eliminarProductoDelete(
            @PathVariable("productoid") long productoid
    ) {
        ResponseEntity<MainResponse> entity = null;
        MainResponse response = new MainResponse();
        try {
            productoService.eliminarProducto(productoid);
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
