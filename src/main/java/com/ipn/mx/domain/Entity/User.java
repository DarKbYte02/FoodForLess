package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name="User",schema = "public")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Size(min=4, max=50,message = "El nombre del usuario debe tener entre 4 y 50 caracteres")
    @Column(name="nombre",length = 50, nullable = false)
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @NotNull(message = "El nombre del usuario no puede ser nulo")
    private String nombreUsuario;

    @Size(min=4, max=50,message = "La contraseña debe tener entre 4 y 50 caracteres")
    @Column(name="contrasena",length = 50, nullable = false)
    @NotBlank(message = "La contraseña del usuario no puede estar vacía")
    @NotNull(message = "La contraseña del usuario no puede ser nula")
    private String contrasenaUsuario;

    @Size(min=4, max=50,message = "El correo del usuario debe tener entre 4 y 50 caracteres")
    @Column(name="correo",length = 50, nullable = false)
    @NotBlank(message = "El correo del usuario no puede estar vacío")
    @NotNull(message = "El correo del usuario no puede ser nulo")
    @Email(message = "El correo del usuario no es válido")
    private String correoUsuario;

    @Column(name="imagenUsuario",length = 250, nullable = true)
    private String imagenUsuario;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Lugar> lugares;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;

    public User(Long id) {
        this.idUser = id;
    }
}
