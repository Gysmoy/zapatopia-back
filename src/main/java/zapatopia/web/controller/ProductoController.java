package zapatopia.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import zapatopia.web.dto.ProductoVentaDto;
import zapatopia.web.jpa.CategoriaJpa;
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

        }catch (Exception e) {
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


}
