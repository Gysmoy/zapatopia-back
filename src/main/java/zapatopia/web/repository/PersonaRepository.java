package zapatopia.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zapatopia.web.jpa.PersonaJpa;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaJpa, Long> {
}