package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.dto.ApiResponse;
import zapatopia.web.dto.RolDto;
import zapatopia.web.jpa.RolJpa;
import zapatopia.web.services.RolService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRoles() {
        List<RolJpa> roles = rolService.getAllRoles();
        return ResponseEntity.ok(new ApiResponse("Roles recuperados exitosamente", roles, true));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> createRol(@RequestBody RolDto rolDto) {
        RolJpa newRol = rolService.createRol(rolDto);
        return ResponseEntity.status(201).body(new ApiResponse("Role created successfully", newRol, true));
    }
}
