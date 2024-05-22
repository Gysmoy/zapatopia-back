package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.dto.UsuarioDto;
import zapatopia.web.jpa.PersonaJpa;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.jpa.UsuarioJpa;
import zapatopia.web.repository.PersonaRepository;
import zapatopia.web.repository.RolRepository;
import zapatopia.web.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RolRepository rolRepository;

    public List<UsuarioJpa> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioJpa> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioJpa createUsuario(UsuarioDto usuarioDto) {
        UsuarioJpa usuario = new UsuarioJpa();
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setContrasenia(usuarioDto.getContrasenia());
        usuario.setCorreoRecuperacion(usuarioDto.getCorreoRecuperacion());

        // Buscar y asignar Persona
        PersonaJpa persona = personaRepository.findById(usuarioDto.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona not found"));
        usuario.setPersona(persona);

        // Buscar y asignar Rol
        RolJpa rol = rolRepository.findById(usuarioDto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol not found"));
        usuario.setRol(rol);

        LocalDateTime now = LocalDateTime.now();
        usuario.setFechaCreacion(now);
        usuario.setFechaModificacion(now);

        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioJpa> updateUsuario(Long id, UsuarioDto usuarioDto) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setUsuario(usuarioDto.getUsuario());
            usuario.setContrasenia(usuarioDto.getContrasenia());
            usuario.setCorreoRecuperacion(usuarioDto.getCorreoRecuperacion());

            // Buscar y asignar Persona
            PersonaJpa persona = personaRepository.findById(usuarioDto.getPersonaId())
                    .orElseThrow(() -> new RuntimeException("Persona not found"));
            usuario.setPersona(persona);

            // Buscar y asignar Rol
            RolJpa rol = rolRepository.findById(usuarioDto.getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol not found"));
            usuario.setRol(rol);

            usuario.setFechaModificacion(LocalDateTime.now());
            return usuarioRepository.save(usuario);
        });
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
