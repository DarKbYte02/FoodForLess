package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "El total del pedido no puede ser nulo")
    private double totalPedido;

    @Column(name="estadoPedido", nullable = false)
    @NotNull(message = "El estado del pedido no puede ser nulo")
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

    public Pedido(Long id) {
        this.idPedido = id;
    }

    @JsonSetter("user")
    public void setUser(Long userId) {
        if (userId==null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
        this.user = new User(userId);
    }

    @JsonSetter("lugar")
    public void setLugar(Long lugarId) {
        if (lugarId==null) {
            throw new IllegalArgumentException("El ID del lugar no puede ser nulo");
        }
        this.lugar = new Lugar(lugarId);
    }

}
