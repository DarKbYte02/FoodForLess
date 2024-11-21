package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Pedido",schema = "public")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @Column(name="totalPedido", nullable = false)
    private double totalPedido;

    @Column(name="estadoPedido", nullable = false)
    private int estadoPedido;

    //IdArticulo
/*    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="idArticulo")
//    @JsonIgnoreProperties({"pedidos"})
//    @JsonBackReference
//    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
      private Articulo articulo;*/

    //Relacion con DetallePedido
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"pedido"})
    private List<DetallePedido> detallePedidos;

    @JsonIgnore
    public List<Articulo> getArticulo() {
        return detallePedidos.stream()
                .map(DetallePedido::getArticulo)
                .distinct()
                .collect(Collectors.toList());
    }

}
