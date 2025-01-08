package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name="Pedido",schema = "public")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @Column(name="totalPedido", nullable = false)
    private double totalPedido;

    @Column(name="estadoPedido", nullable = false)
    private int estadoPedido;

   //IdUsuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUser")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"nombreUsuario","contrsenaUsuario","correoUsuario","imagenUsuario"})
    private User user;

    //IdArticulo
/*    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="idArticulo")
//    @JsonIgnoreProperties({"pedidos"})
//    @JsonBackReference
//    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
      private Articulo articulo;*/

    //Relacion con DetallePedido
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DetallePedido> detallePedidos;

//    public List<Articulo> getArticulo() {
//        return detallePedidos.stream()
//                .map(DetallePedido::getArticulo)
//                .distinct()
//                .collect(Collectors.toList());
//    }

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;

    //idLugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idLugar")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"idUser","nombreLugar","direccionLugar","descripcionLugar","imagenLugar","latitudLugar","longitudLugar","horaApertura","horaCierre","calificacionTotal","user"})
    private Lugar lugar;
}
