package com.ipn.mx.domain.Repository;

import com.ipn.mx.domain.Entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedidoIdPedido(Long idPedido);
    List<DetallePedido> findByArticuloIdArticulo(Long idArticulo);
    void deleteByPedidoIdPedido(Long idPedido);
}
