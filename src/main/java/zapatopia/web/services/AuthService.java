package zapatopia.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zapatopia.web.jpa.UsuarioJpa;
import zapatopia.web.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validateLogin(String usuario, String contrasenia) {
        UsuarioJpa user = usuarioRepository.findByUsuario(usuario);
        return user != null && user.getContrasenia().equals(contrasenia);
    }
}