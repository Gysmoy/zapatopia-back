package zapatopia.web.repository;
import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.AlmacenJpa;

import java.util.List;

public interface AlmacenRepository extends CrudRepository<AlmacenJpa, Long> {
    List<AlmacenJpa> findAll();
}