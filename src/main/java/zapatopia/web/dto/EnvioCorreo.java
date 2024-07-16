package zapatopia.web.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EnvioCorreo {
    private String destino;
    private String asunto;
    private Map<String, Object> variables;
    private Map<String, String> imagenesAdjuntas;
    private Map<String, String> documentosAdjuntos;
    private String plantilla;

    public EnvioCorreo(){}

    public EnvioCorreo(String destino, String asunto, Map<String, Object> variables, String plantilla) {
        this.destino = destino;
        this.asunto = asunto;
        this.variables = variables == null ? new HashMap<>() : variables;
        this.plantilla = plantilla;
        this.imagenesAdjuntas = new HashMap<>();
        this.documentosAdjuntos = new HashMap<>();
    }

    public EnvioCorreo(String destino, String asunto, Map<String, Object> variables, Map<String, String> imagenesAdjuntas, Map<String, String> documentosAdjuntos, String plantilla) {
        this.destino = destino;
        this.asunto = asunto;
        this.variables = variables == null ? new HashMap<>() : variables;
        this.imagenesAdjuntas = imagenesAdjuntas == null ? new HashMap<>() : imagenesAdjuntas;
        this.documentosAdjuntos = documentosAdjuntos == null ? new HashMap<>() : documentosAdjuntos;
        this.plantilla = plantilla;
    }
}
