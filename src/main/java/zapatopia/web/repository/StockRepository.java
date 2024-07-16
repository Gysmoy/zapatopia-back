package zapatopia.web.repository;

import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.StockJpa;

public interface StockRepository extends CrudRepository<StockJpa, Long> {
}
