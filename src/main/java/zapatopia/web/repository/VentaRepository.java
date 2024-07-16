package zapatopia.web.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zapatopia.web.jpa.VentaJpa;

@Repository
public interface VentaRepository extends CrudRepository<VentaJpa, Long> {

    @Transactional
    @Modifying
    @Query("update VentaJpa v set v.estado = (select e from EstadoJpa e where e.id = :idEstado) where v.id = :idVenta")
    int actualizarEstado(@Param("idVenta") Long idVenta, @Param("idEstado") Long idEstado);

}
