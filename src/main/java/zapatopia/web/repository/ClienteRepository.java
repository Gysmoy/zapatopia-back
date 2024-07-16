package zapatopia.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zapatopia.web.jpa.ClienteJpa;

import java.util.List;

public interface ClienteRepository extends CrudRepository<ClienteJpa, Long> {

    @Query("select c from ClienteJpa c where flagNotificar = 'S'")
    List<ClienteJpa> listarNotificados();

}
