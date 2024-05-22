package zapatopia.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zapatopia.web.jpa.UsuarioJpa;

public interface UsuarioRepository extends JpaRepository<UsuarioJpa, Long> {
    UsuarioJpa findByUsuario(String usuario);

}