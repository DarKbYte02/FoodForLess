package com.ipn.mx.domain.Repository;


import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    @Query("SELECT a FROM Articulo a JOIN FETCH a.categoria WHERE a.idArticulo = :id")
    Articulo findArticuloWithCategoria(@Param("id") Long id);

    @Query("SELECT a FROM Articulo a JOIN FETCH a.categoria")
    List<Articulo> findAllArticulosWithCategoria();

    @Query("SELECT a FROM Articulo a JOIN FETCH a.categoria WHERE a.categoria.idCategoria = :idCategoria")
    List<Articulo> findByCategoriaIdCategoria(@Param("idCategoria") Long idCategoria);
}
