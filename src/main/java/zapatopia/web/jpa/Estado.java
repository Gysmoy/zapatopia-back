package zapatopia.web.jpa;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ESTADOS")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "descripcion")
    private String descripcion;
}