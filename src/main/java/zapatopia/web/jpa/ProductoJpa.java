package zapatopia.web.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "categoria_id")
    private CategoriaJpa categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private MarcaJpa marca;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "talla")
    private Integer talla;

    @Column(name = "color")
    private String color;

    @Column(name = "genero")
    private String genero;

    @Column(name = "path_foto")
    private String pathFoto;

    @Column(name = "stock_general")
    private Integer stockGeneral;

    @JsonIgnore
    @OneToMany(mappedBy="producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockJpa> stockAlmacenes;

    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
