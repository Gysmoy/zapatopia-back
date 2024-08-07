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
@Table(name = "DETALLE_VENTAS")
public class DetalleVentasJpa {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private VentaJpa venta;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockJpa stock;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private Double precio;

    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Override
    public String toString() {
        return "DetalleVentasJpa{" +
                "id=" + id +
                ", producto=" + ((stock == null || stock.getProducto() == null) ? "null" : stock.getProducto().getNombre()) +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
