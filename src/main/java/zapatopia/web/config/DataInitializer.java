package zapatopia.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zapatopia.web.jpa.PersonaJpa;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.jpa.UsuarioJpa;
import zapatopia.web.repository.PersonaRepository;
import zapatopia.web.repository.RolRepository;
import zapatopia.web.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        if (!defaultRecordsExist()) {
            insertDefaultRecords();
        }
    }

    @Transactional
    private boolean defaultRecordsExist() {
        return personaRepository.existsByNombres("Admin")
                && rolRepository.existsByRol("ADMIN")
                && usuarioRepository.existsByUsuario("root");
    }

    @Transactional
    private void insertDefaultRecords() {
        // Crear el rol administrador
        RolJpa adminRole = new RolJpa();
        adminRole.setRol("ADMIN");
        adminRole.setDescripcion("Rol administrador");
        adminRole.setPermisos("FULL_ACCESS");
        adminRole.setFechaCreacion(LocalDateTime.now());
        adminRole.setFechaModificacion(LocalDateTime.now());
        rolRepository.save(adminRole);

        // Crear la persona
        PersonaJpa defaultPerson = new PersonaJpa();
        defaultPerson.setTipoDocumento("DNI");
        defaultPerson.setNumeroDocumento("12345678");
        defaultPerson.setNombres("Admin");
        defaultPerson.setApellidoPaterno("User");
        defaultPerson.setApellidoMaterno("Default");
        defaultPerson.setDireccion("Default Address");
        defaultPerson.setNumeroCelular("123456789");
        defaultPerson.setFechaCreacion(LocalDateTime.now());
        defaultPerson.setFechaModificacion(LocalDateTime.now());
        personaRepository.save(defaultPerson);

        // Crear el usuario
        UsuarioJpa defaultUser = new UsuarioJpa();
        defaultUser.setPersona(defaultPerson);
        defaultUser.setUsuario("root");
        defaultUser.setContrasenia("r00tme");
        defaultUser.setCorreoRecuperacion("admin@example.com");
        defaultUser.setRol(adminRole);
        defaultUser.setFechaCreacion(LocalDateTime.now());
        defaultUser.setFechaModificacion(LocalDateTime.now());
        usuarioRepository.save(defaultUser);
    }
}
