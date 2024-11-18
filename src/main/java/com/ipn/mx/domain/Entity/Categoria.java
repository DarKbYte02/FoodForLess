package com.ipn.mx.domain.Entity;

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
@Table(name="Categoria",schema = "public")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Size(min=4, max=150,message = "El nombre de la categoria debe tener entre 4 y 150 caracteres")
    @Column(name="nombreCategoria",length = 150, nullable = false)
    private String nombreCategoria;

    @Column(name="imagenCategoria",length = 250, nullable = false)
    private String imagenCategoria;

}
