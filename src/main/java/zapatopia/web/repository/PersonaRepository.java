package zapatopia.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zapatopia.web.jpa.PersonaJpa;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaJpa, Long> {
    Boolean existsByNombres(String nombres);
    Optional<PersonaJpa> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}