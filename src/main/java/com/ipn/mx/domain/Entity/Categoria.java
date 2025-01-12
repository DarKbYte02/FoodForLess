package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
    @NotEmpty(message = "El nombre de la categoria no puede estar vacio")
    @NotNull(message = "El nombre de la categoria no puede ser nulo")
    @Column(name="nombreCategoria",length = 150, nullable = false)
    private String nombreCategoria;

    @Column(name="imagenCategoria",length = 250, nullable = false)
    private String imagenCategoria;

    //Relacion con Articulo
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Articulo> articulos;

    public Categoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

}
