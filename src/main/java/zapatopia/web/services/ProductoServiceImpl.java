package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.jpa.ProductoJpa;
import zapatopia.web.repository.MarcaRepository;
import zapatopia.web.repository.ProductoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoJpa> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoJpa obtenerProducto(long id) {
        return productoRepository.findById(id).isPresent() ? productoRepository.findById(id).get() : null;
    }

    @Override
    public List<ProductoJpa> obtenerProductosPorListaId(List<Long> listaId) {
        return productoRepository.findByIdIn(listaId);
    }

    @Override
    public ProductoJpa guardarProducto(ProductoJpa producto) {
        ProductoJpa productoJpa = productoRepository.findById(producto.getId()).orElse(new ProductoJpa());
        productoJpa.setNombre(producto.getNombre());
        productoJpa.setCategoria(producto.getCategoria());
        productoJpa.setMarca(producto.getMarca());
        productoJpa.setTalla(producto.getTalla());
        productoJpa.setColor(producto.getColor());
        productoJpa.setGenero(producto.getGenero());

        Long stockGeneral = 0L;

        if (producto.getId() == 0) {
            producto.setFechaCreacion(LocalDateTime.now());
        }
        producto.setFechaModificacion(LocalDateTime.now());
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(long id) {
        productoRepository.deleteById(id);
    }
}
