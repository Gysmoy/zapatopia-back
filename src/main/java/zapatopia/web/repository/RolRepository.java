package zapatopia.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zapatopia.web.jpa.RolJpa;

@Repository
public interface RolRepository extends JpaRepository<RolJpa, Long> {
}