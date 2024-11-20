package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer","detallePedidos"})
    private Articulo articulo;

    //IdPedido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idPedido")
    @JsonIgnoreProperties({"hibernateLazyInitializer","detallePedidos"})
    private Pedido pedido;


}
