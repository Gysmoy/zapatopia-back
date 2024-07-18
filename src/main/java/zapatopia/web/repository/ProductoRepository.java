package zapatopia.web.repository;
import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.ProductoJpa;

import java.util.List;

public interface ProductoRepository extends CrudRepository<ProductoJpa, Long> {
    List<ProductoJpa> findByIdIn(List<Long> ids);
}