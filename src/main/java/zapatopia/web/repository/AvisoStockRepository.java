package zapatopia.web.repository;

import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.AvisoStockJpa;

import java.util.List;

public interface AvisoStockRepository extends CrudRepository<AvisoStockJpa, Long> {
    List<AvisoStockJpa> findAllByProductId(long productId);
}
