package zapatopia.web.repository;

import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.EstadoJpa;

import java.util.Optional;

public interface EstadoRepository extends CrudRepository<EstadoJpa, Long> {

    Optional<EstadoJpa> findByEstado(String estado);

}
