package zapatopia.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zapatopia.web.dto.ApiResponse;
import zapatopia.web.dto.LoginRequestDto;
import zapatopia.web.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto loginRequest) {
        boolean isValid = authService.validateLogin(loginRequest.getUsuario(), loginRequest.getContrasenia());
        if (isValid) {
            return ResponseEntity.ok(new ApiResponse("Se ha logueado correctamente", null, true));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse("Credenciales invalidas", null, false));
        }
    }
}