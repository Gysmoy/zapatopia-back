package zapatopia.web.repository;
import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.CategoriaJpa;
import zapatopia.web.jpa.MarcaJpa;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<CategoriaJpa, Long> {

    List<CategoriaJpa> findAll();

}