package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"articulos","categoria"})
    private Categoria categoria;

/*    Relacion con Pedido
    @OneToMany(mappedBy = "articulo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"articulo"})
    @JsonManagedReference
    private List<Pedido> pedidos;*/

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

}
