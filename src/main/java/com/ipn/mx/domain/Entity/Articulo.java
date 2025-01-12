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
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
@Table(name="Articulo",schema = "public")
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;

    @Size(min=4, max=150,message = "El nombre del articulo debe tener entre 4 y 150 caracteres")
    @Column(name="nombreArticulo",length = 150, nullable = false)
    @NotNull(message = "El nombre del articulo no puede ser nulo")
    private String nombreArticulo;

    @Column(name="stock", nullable = false)
    @NotNull(message = "El stock del articulo no puede ser nulo")
    private int stock;

    @Size(min=4, max=150,message = "La descripcion del articulo debe tener entre 4 y 150 caracteres")
    @Column(name="descripcionArticulo",length = 150, nullable = false)
    private String descripcionArticulo;

    @Column(name="precioArticulo", nullable = false)
    @NotNull(message = "El precio del articulo no puede ser nulo")
    @NotBlank(message = "El precio del articulo no puede estar vacio")
    private double precioArticulo;

    @Column(name="imagenArticulo",length = 250, nullable = false)
    private String imagenArticulo;

    @Column(name="tiempoInicial", nullable = false)
    @NotNull(message = "El tiempo inicial del articulo no puede ser nulo")
    @Temporal(TemporalType.TIME)
    private Date tiempoInicial;

    @Column(name="tiempoFinal", nullable = false)
    @NotNull(message = "El tiempo final del articulo no puede ser nulo")
    @Temporal(TemporalType.TIME)
    private Date tiempoFinal;

    //Idcategoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCategoria")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"articulos","nombreCategoria","imagenCategoria"})
    private Categoria categoria;

    //IdLugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idLugar")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"nombreLugar, direccionLugar, descripcionLugar, imagenLugar, latitudLugar, longitudLugar, horaApertura, horaCierre, calificacionTotal"})
    private Lugar lugar;

    //Relacion con DetallePedido
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DetallePedido> detallePedidos;
//    public List<Pedido> getPedidos() {
//        return detallePedidos.stream()
//                .map(DetallePedido::getPedido)
//                .distinct()
//                .collect(Collectors.toList());
//    }

    public Articulo(Long id) {
        this.idArticulo = id;
    }

    @JsonSetter("categoria")
    public void setCategoria(Long categoriaId) {
        if (categoriaId==null) {
            throw new IllegalArgumentException("El ID de la categoria no puede ser nulo");
        }
        this.categoria = new Categoria(categoriaId);
    }

    @JsonSetter("lugar")
    public void setLugar(Long lugarId) {
        if (lugarId==null) {
            throw new IllegalArgumentException("El ID del lugar no puede ser nulo");
        }
        this.lugar = new Lugar(lugarId);
    }

}
