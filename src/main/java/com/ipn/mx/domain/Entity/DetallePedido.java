package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="DetallePedido",schema = "public")
public class DetallePedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idDetallePedido;

    @Column(name="cantidadPedido", nullable = false)
    private int cantidadPedido;

    @Column(name="precioPedido", nullable = false)
    private double precioPedido;

    //IdArticulo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idArticulo")
    @JsonIgnore
    private Articulo articulo;

    //IdPedido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idPedido")
    @JsonIgnore
    private Pedido pedido;

}
