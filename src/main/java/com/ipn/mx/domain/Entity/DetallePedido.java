package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "La cantidad del pedido es requerida")
    private int cantidadPedido;

    @Column(name="precioPedido", nullable = false)
    @NotBlank(message = "El precio del pedido es requerido")
    private double precioPedido;

    //IdArticulo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idArticulo")
    @JsonIgnoreProperties({"nombreArticulo","descripcionArticulo","precioArticulo","imagenArticulo","tiempoInicial","tiempoFinal","categoria","lugar","stock"})
    private Articulo articulo;
    //IdPedido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idPedido")
    @JsonIgnoreProperties({"totalPedido","estadoPedido","user","reviews","lugar"})
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    private Pedido pedido;

    @JsonSetter("articulo")
    public void setArticulo(Long articuloId) {
        if (articuloId==null) {
            return;
        }
        this.articulo = articuloId!=null ? Articulo.builder().idArticulo(articuloId).build() : null;
    }

    @JsonSetter("pedido")
    public void setPedido(Long pedidoId) {
        if (pedidoId==null) {
            return;
        }
        this.pedido = pedidoId!=null ? Pedido.builder().idPedido(pedidoId).build() : null;
    }
}
