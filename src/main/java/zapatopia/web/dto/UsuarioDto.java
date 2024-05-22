package zapatopia.web.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioDto {
    private long personaId;
    private String usuario;
    private String contrasenia;
    private String correoRecuperacion;
    private long rolId;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}