package zapatopia.web.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zapatopia.web.jpa.MarcaJpa;
import zapatopia.web.jpa.PersonaJpa;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.jpa.UsuarioJpa;
import zapatopia.web.repository.MarcaRepository;
import zapatopia.web.repository.PersonaRepository;
import zapatopia.web.repository.RolRepository;
import zapatopia.web.repository.UsuarioRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Value("${imagenes.path}")
    private String pathImagenes;

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

        //crearMarcas();
    }

    @PostConstruct
    private void crearCarpetaImagenes() {
        Path path = Paths.get(pathImagenes);

        if (!Files.exists(path)) {
            try {
                Path imagenes = Files.createDirectory(path);
                log.info("se creo carpeta: {}", imagenes.toAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
          log.info("Ya existe carpeta: {}", path.toAbsolutePath());
        }
    }


    private void crearMarcas() {


        List<MarcaJpa> marcas = new ArrayList<>();

        // Crear marcas
        MarcaJpa defaultMarcaNike = new MarcaJpa();
        defaultMarcaNike.setMarca("Nike");
        defaultMarcaNike.setDescripcion("Nike");
        defaultMarcaNike.setFechaCreacion(LocalDateTime.now());
        defaultMarcaNike.setFechaModificacion(LocalDateTime.now());
        marcas.add(defaultMarcaNike);

        MarcaJpa defaultMarcaAdidas = new MarcaJpa();
        defaultMarcaAdidas.setMarca("Adidas");
        defaultMarcaAdidas.setDescripcion("Adidas");
        defaultMarcaAdidas.setFechaCreacion(LocalDateTime.now());
        defaultMarcaAdidas.setFechaModificacion(LocalDateTime.now());
        marcas.add(defaultMarcaAdidas);

        MarcaJpa defaultMarcaPuma = new MarcaJpa();
        defaultMarcaPuma.setMarca("Puma");
        defaultMarcaPuma.setDescripcion("Puma");
        defaultMarcaPuma.setFechaCreacion(LocalDateTime.now());
        defaultMarcaPuma.setFechaModificacion(LocalDateTime.now());
        marcas.add(defaultMarcaPuma);

        MarcaJpa defaultMarcaConverse = new MarcaJpa();
        defaultMarcaConverse.setMarca("Converse");
        defaultMarcaConverse.setDescripcion("Converse");
        defaultMarcaConverse.setFechaCreacion(LocalDateTime.now());
        defaultMarcaConverse.setFechaModificacion(LocalDateTime.now());
        marcas.add(defaultMarcaConverse);

        MarcaJpa defaultMarcaVans = new MarcaJpa();
        defaultMarcaVans.setMarca("Vans");
        defaultMarcaVans.setDescripcion("Vans");
        defaultMarcaVans.setFechaCreacion(LocalDateTime.now());
        defaultMarcaVans.setFechaModificacion(LocalDateTime.now());
        marcas.add(defaultMarcaVans);

        marcaRepository.saveAll(marcas);

    }


}
