package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zapatopia.web.dto.UsuarioDto;
import zapatopia.web.jpa.UsuarioJpa;
import zapatopia.web.services.UsuarioService;
import zapatopia.web.dto.ApiResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsuarios() {
        List<UsuarioJpa> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(new ApiResponse("Usuarios retrieved successfully", usuarios, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioJpa> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(new ApiResponse("Usuario retrieved successfully", usuario.get(), true));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Usuario not found", null, false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createUsuario(@RequestBody UsuarioDto usuarioDto) {
        UsuarioJpa newUsuario = usuarioService.createUsuario(usuarioDto);
        return ResponseEntity.status(201).body(new ApiResponse("Usuario created successfully", newUsuario, true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        Optional<UsuarioJpa> updatedUsuario = usuarioService.updateUsuario(id, usuarioDto);
        if (updatedUsuario.isPresent()) {
            return ResponseEntity.ok(new ApiResponse("Usuario updated successfully", updatedUsuario.get(), true));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse("Usuario not found", null, false));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok(new ApiResponse("Usuario deleted successfully", null, true));
    }
}
