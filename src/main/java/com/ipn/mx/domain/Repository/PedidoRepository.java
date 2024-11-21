package com.ipn.mx.domain.Repository;

import com.ipn.mx.domain.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
//    @Query("SELECT a FROM Pedido a JOIN FETCH a.articulo WHERE a.idPedido = :id")
//    Pedido findPedidoWithArticulo(Long id);
//
//    @Query("SELECT a FROM Pedido a JOIN FETCH a.articulo")
//    List<Pedido> findAllPedidosWithArticulo();
//
//    @Query("SELECT a FROM Pedido a JOIN FETCH a.articulo WHERE a.articulo.idArticulo = :idArticulo")
//    List<Pedido> findByArticuloIdArticulo(@Param("idArticulo") Long idArticulo);
}
