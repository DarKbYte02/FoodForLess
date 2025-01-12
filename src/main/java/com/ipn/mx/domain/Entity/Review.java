package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name="Review",schema = "public")

public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @Column(name="calificacion", nullable = false)
    @NotNull(message = "La calificacion del review no puede ser nula")
    private double calificacion;

    @Size(min=4, max=100,message = "La descripcion del review debe tener entre 4 y 100 caracteres")
    @Column(name="descripcionReview", length = 100, nullable = true)
    private String descripcionReview;

    @Column(name="imagenReview",length = 250, nullable = true)
    private String imagenReview;

    //IdUsuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUser")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"reviews"})
    private User user;

    //IdLugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idLugar")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"reviews"})
    private Lugar lugar;

    //IdPedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idPedido")
    @JsonIdentityReference(alwaysAsId = true) // Serializar como id
    @JsonIgnoreProperties({"idLugar","idUser","totalPedido","estadoPedido"})
    private Pedido pedido;

    @JsonSetter("lugar")
    public void setLugar(Long lugarId) {
        if (lugarId==null) {
            throw new IllegalArgumentException("El ID del lugar no puede ser nulo");
        }
        this.lugar = new Lugar(lugarId);
    }

    @JsonSetter("user")
    public void setUser(Long userId) {
        if (userId==null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
        this.user = new User(userId);
    }

    @JsonSetter("pedido")
    public void setPedido(Long pedidoId) {
        if (pedidoId==null) {
            throw new IllegalArgumentException("El ID del pedido no puede ser nulo");
        }
        this.pedido = new Pedido(pedidoId);
    }
}
