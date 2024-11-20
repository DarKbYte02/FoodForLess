package com.ipn.mx.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @Column(name="nombreCategoria",length = 150, nullable = false)
    private String nombreCategoria;

    @Column(name="imagenCategoria",length = 250, nullable = false)
    private String imagenCategoria;

    //Relacion con Articulo
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"categoria"})
    @JsonBackReference
    private List<Articulo> articulos;

}
