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
@Table(name = "STOCK_SUBSCRIPTIONS")
public class AvisoStockJpa {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client_id")
    private long clientId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "email")
    private String email;

    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
