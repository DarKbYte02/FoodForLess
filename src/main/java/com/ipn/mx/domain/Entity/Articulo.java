package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private String nombreArticulo;

    @Column(name="stock", nullable = false)
    private int stock;

    @Size(min=4, max=150,message = "La descripcion del articulo debe tener entre 4 y 150 caracteres")
    @Column(name="descripcionArticulo",length = 150, nullable = false)
    private String descripcionArticulo;

    @Column(name="precioArticulo", nullable = false)
    private double precioArticulo;

    @Column(name="imagenArticulo",length = 250, nullable = false)
    private String imagenArticulo;

    @Column(name="tiempoInicial", nullable = false)
    private Date tiempoInicial;

    @Column(name="tiempoFinal", nullable = false)
    private Date tiempoFinal;

    //Idcategoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCategoria")
    @JsonIgnoreProperties({"articulos"})
    @JsonBackReference
    private Categoria categoria;

    //Relacion con Pedido
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"articulo"})
    @JsonManagedReference
    private List<Pedido> pedidos;

}
