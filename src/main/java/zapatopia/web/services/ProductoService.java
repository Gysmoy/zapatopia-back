package zapatopia.web.services;

import zapatopia.web.dto.ProductoVentaDto;
import zapatopia.web.jpa.ProductoJpa;

import java.util.List;

public interface ProductoService {

    List<ProductoJpa> listarProductos();
    List<ProductoVentaDto> listarProductoVentas();
    ProductoJpa obtenerProducto(long id);
    ProductoJpa crearProducto(ProductoJpa producto);
    ProductoJpa actualizarProducto(ProductoJpa producto, long id);
    void eliminarProducto(long id);


}
