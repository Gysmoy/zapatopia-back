package zapatopia.web.repository;

import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.OfertaJpa;

public interface OfertaRepository extends CrudRepository<OfertaJpa, Long> {
}
