package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Lugar",schema = "public")

public class Lugar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLugar;

    @Size(min=4, max=150,message = "El nombre del lugar debe tener entre 4 y 150 caracteres")
    @Column(name="nombreLugar",length = 150, nullable = false)
    private String nombreLugar;

    @Size(min=10, max=150,message = "La direccion del lugar debe tener entre 10 y 150 caracteres")
    @Column(name="direccionLugar",length = 150, nullable = false)
    @NotBlank(message = "La direccion del lugar no puede estar vacia")
    @NotNull(message = "La direccion del lugar no puede ser nula")
    private String direccionLugar;

    @Size(min=10, max=150,message = "La descripcion del lugar debe tener entre 10 y 150 caracteres")
    @Column(name="descripcionLugar",length = 150, nullable = false)
    private String descripcionLugar;

    @Column(name="imagenLugar",length = 250, nullable = false)
    private String imagenLugar;

    @Column(name="latitudLugar", nullable = false)
    @NotNull(message = "La latitud del lugar no puede ser nula")
    @NotBlank(message = "La latitud del lugar no puede estar vacia")
    private double latitudLugar;

    @Column(name="longitudLugar", nullable = false)
    @NotNull(message = "La longitud del lugar no puede ser nula")
    @NotBlank(message = "La longitud del lugar no puede estar vacia")
    private double longitudLugar;

    @Column(name="horaApertura", nullable = false)
    @NotNull(message = "La hora de apertura del lugar no puede ser nula")
    @NotBlank(message = "La hora de apertura del lugar no puede estar vacia")
    private int horaApertura;

    @Column(name="horaCierre", nullable = false)
    @NotNull(message = "La hora de cierre del lugar no puede ser nula")
    @NotBlank(message = "La hora de cierre del lugar no puede estar vacia")
    private int horaCierre;

    @Column(name="calificacionTotal", nullable = false)
    private double calificacionTotal;

    //IdUsuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idUser")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"nombreUsuario","contrasenaUsuario","correoUsuario","imagenUsuario"})
    private User user;

    //Relacion con Articulo
    @OneToMany(mappedBy = "lugar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Articulo> articulos;

    //Relacion con Review
    @OneToMany(mappedBy = "lugar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;

    //Relacion con Pedido
    @OneToMany(mappedBy = "lugar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pedido> pedidos;

    @JsonSetter("user")
    public void setUser(Long userId) {
        if (userId==null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
       this.user = new User(userId); // Aquí usas el ID para crear una nueva instancia de User
    }

    public Lugar(Long id) {
        this.idLugar = id;
    }
}
