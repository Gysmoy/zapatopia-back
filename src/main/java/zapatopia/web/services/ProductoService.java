package zapatopia.web.services;

import zapatopia.web.jpa.ProductoJpa;

import java.util.List;

public interface ProductoService {
    List<ProductoJpa> obtenerProductos();
    ProductoJpa obtenerProducto(long id);
    List<ProductoJpa> obtenerProductosPorListaId(List<Long> listaId);
    ProductoJpa guardarProducto(ProductoJpa marca);
    void eliminarProducto(long id);
}
