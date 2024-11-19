package com.ipn.mx.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="User",schema = "public")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Size(min=4, max=50,message = "El nombre del usuario debe tener entre 4 y 50 caracteres")
    @Column(name="nombre",length = 50, nullable = false)
    private String nombreUsuario;

    @Size(min=4, max=50,message = "La contraseña debe tener entre 4 y 50 caracteres")
    @Column(name="contrasena",length = 50, nullable = false)
    private String contrasenaUsuario;

    @Size(min=4, max=50,message = "El correo del usuario debe tener entre 4 y 50 caracteres")
    @Column(name="correo",length = 50, nullable = false)
    private String correoUsuario;

    @Column(name="imagenUsuario",length = 250, nullable = true)
    private String imagenUsuario;
}
