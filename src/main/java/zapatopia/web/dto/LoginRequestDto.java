package zapatopia.web.dto;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String usuario;
    private String contrasenia;
}