package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.dto.RolDto;
import zapatopia.web.jpa.PersonaJpa;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.repository.PersonaRepository;
import zapatopia.web.repository.RolRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<PersonaJpa> getAllPersonas() {
        return personaRepository.findAll();
    }

}
