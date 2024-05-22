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
@Table(name = "STOCK")
public class StockJpa {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "almacenId")
    private AlmacenJpa almacen;

    @ManyToOne
    @JoinColumn(name = "productoId")
    private ProductoJpa producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
