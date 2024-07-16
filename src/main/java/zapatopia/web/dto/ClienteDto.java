package zapatopia.web.dto;

import lombok.Data;

@Data
public class ClienteDto {

    private long idCliente;
    private long idPersona;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String correoElectronico;
    private String numeroCelular;
    private boolean flagNotificar;
    private String medioPreferido;
}
