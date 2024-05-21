package zapatopia.web.jpa;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ALMACENES")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "almacen")
    private String almacen;
    @Column(name = "direccion")
    private String direccion;
}