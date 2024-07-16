package zapatopia.web.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import zapatopia.web.dto.ProductoVentaDto;
import zapatopia.web.jpa.ProductoJpa;
import zapatopia.web.repository.ProductoRepository;
import zapatopia.web.repository.StockRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<ProductoJpa> listarProductos() {
        return StreamSupport.stream(productoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoVentaDto> listarProductoVentas() {
        List<ProductoVentaDto> productos = new ArrayList<>();
        stockRepository.findAll().forEach(stock -> {
            ProductoJpa productoEntity = stock.getProducto();
            ProductoVentaDto productoDto = new ProductoVentaDto();
            productoDto.setIdStock(stock.getId());
            productoDto.setId(productoEntity.getId());
            productoDto.setCategoria(productoEntity.getCategoria());
            productoDto.setMarca(productoEntity.getMarca());
            productoDto.setNombre(productoEntity.getNombre());
            productoDto.setDescripcion(productoEntity.getDescripcion());
            productoDto.setPrecioVenta(stock.getPrecioVenta());
            productoDto.setTalla(productoEntity.getTalla());
            productoDto.setColor(productoEntity.getColor());
            productoDto.setGenero(productoEntity.getGenero());
            productoDto.setPathFoto(construirPathFoto(productoEntity.getPathFoto()));
            productos.add(productoDto);
        });

        return productos;
    }

    private String construirPathFoto(String imagePath) {
        ServletRequestAttributes  attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // Construir la URL de la imagen dinámicamente
        String scheme = request.getScheme(); // "http" or "https"
        String host = request.getServerName(); // Nombre del host
        int port = request.getServerPort(); // Puerto
        String contextPath = request.getContextPath();

        return scheme + "://" + host + ":" + port + contextPath + imagePath;
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
    public ProductoJpa crearProducto(ProductoJpa producto) {

        //aca hay que implementar la logica de el gusrdado del detalle de stock
        //tambien el guardado de las fotos en la carpeta img
        return productoRepository.save(producto);

    }

    @Override
    public ProductoJpa actualizarProducto(ProductoJpa producto, long id) {

        //modificar para el correcto guardado del detalle
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
