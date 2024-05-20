package com.zapatopia.web.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Table(name = "USUARIOS")
@EntityListeners(AuditingEntityListener.class)
public class UserJpa {

    private static final long serialVersionUID = 1234567L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="USUARIO")
    private String usuario;

    @Column(name ="PASSWORD")
    private String password;
}
