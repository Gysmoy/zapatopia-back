package zapatopia.web.models;

import lombok.Data;

import java.util.List;

@Data
public class MainResponse {
    private int status;
    private String message;
    private Object data;
}
