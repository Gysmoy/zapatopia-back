package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.dto.ApiResponse;
import zapatopia.web.dto.RolDto;
import zapatopia.web.jpa.PersonaJpa;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.services.PersonaService;
import zapatopia.web.services.RolService;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRoles() {
        List<PersonaJpa> personas = personaService.getAllPersonas();
        return ResponseEntity.ok(new ApiResponse("Personas recuperadas exitosamente", personas, true));
    }

}