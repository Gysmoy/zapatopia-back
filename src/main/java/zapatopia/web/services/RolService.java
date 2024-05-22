package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.dto.RolDto;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.repository.RolRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<RolJpa> getAllRoles() {
        return rolRepository.findAll();
    }

    public RolJpa createRol(RolDto rolDto) {
        RolJpa rol = new RolJpa();
        rol.setRol(rolDto.getRol());
        rol.setDescripcion(rolDto.getDescripcion());
        rol.setPermisos(rolDto.getPermisos());
        LocalDateTime now = LocalDateTime.now();
        rol.setFechaCreacion(now);
        rol.setFechaModificacion(now);
        return rolRepository.save(rol);
    }
}
