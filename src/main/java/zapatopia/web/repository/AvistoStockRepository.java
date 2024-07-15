package zapatopia.web.repository;

import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.StockJpa;

import java.util.List;

public interface StockRepository extends CrudRepository<StockJpa, Long> {
    List<StockJpa> findAllByProductId(long productId);
}
