package zapatopia.web.jpa;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(name = "medio_preferido")
    private String medioPreferido;
}