package zapatopia.web.repository;
import org.springframework.data.repository.CrudRepository;
import com.github.ydespreaux.spring.data.jpa.repository.support.JpaCriteriaRepository;
import zapatopia.web.jpa.MarcaJpa;

import java.util.List;

public interface MarcaRepository extends CrudRepository<MarcaJpa, Long> {

    List<MarcaJpa> findAll();

}