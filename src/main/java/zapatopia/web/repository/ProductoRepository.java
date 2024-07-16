package zapatopia.web.repository;

import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.ProductoJpa;

public interface ProductoRepository extends CrudRepository<ProductoJpa, Long> {
}
