package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private String direccionLugar;

    @Size(min=10, max=150,message = "La descripcion del lugar debe tener entre 10 y 150 caracteres")
    @Column(name="descripcionLugar",length = 150, nullable = false)
    private String descripcionLugar;

    @Column(name="imagenLugar",length = 250, nullable = false)
    private String imagenLugar;

    @Column(name="latitudLugar", nullable = false)
    private double latitudLugar;

    @Column(name="longitudLugar", nullable = false)
    private double longitudLugar;

    @Column(name="horaApertura", nullable = false)
    private int horaApertura;

    @Column(name="horaCierre", nullable = false)
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

}
