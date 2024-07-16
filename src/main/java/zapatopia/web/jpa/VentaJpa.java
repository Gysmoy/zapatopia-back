package zapatopia.web.jpa;

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
@Table(name = "VENTAS")
public class VentaJpa {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigo_pedido")
    private String codigoPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteJpa cliente;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private UsuarioJpa usuario;

    @ManyToOne
    @JoinColumn(name = "estadoId")
    private EstadoJpa estado;

    @OneToMany(mappedBy="venta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetalleVentasJpa> detalle;

    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
