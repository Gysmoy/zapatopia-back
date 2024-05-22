package zapatopia.web.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PRODUCTOS")
public class ProductoJpa {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private CategoriaJpa categoria;

    @ManyToOne
    @JoinColumn(name = "marcaId")
    private MarcaJpa marca;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "talla")
    private Integer talla;

    @Column(name = "color")
    private String color;

    @Column(name = "genero")
    private String genero;

    @Column(name = "stock_general")
    private Integer stockGeneral;

    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
