package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

}
