package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.DetallePedido;
import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Repository.ArticuloRepository;
import com.ipn.mx.domain.Repository.DetallePedidoRepository;
import com.ipn.mx.domain.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ArticuloRepository articuloRepository;

    public DetallePedido createDetallePedido(DetallePedido detallePedido) {
        log.info("Creando detalle pedido: " + detallePedido);
        log.info("ID Pedido: " + detallePedido.getPedido().getIdPedido());
        log.info("ID Artículo: " + detallePedido.getArticulo().getIdArticulo());

        //Existencia del pedido
        if(!pedidoRepository.existsById(detallePedido.getPedido().getIdPedido())){
            throw new IllegalArgumentException("El Pedido no existe");
        }

        //Existencia del articulo
        if(!articuloRepository.existsById(detallePedido.getArticulo().getIdArticulo())){
            throw new IllegalArgumentException("El Articulo no existe");
        }

        return detallePedidoRepository.save(detallePedido);
    }

    /**
     * Elimina un DetallePedido por su ID
     */
    public void deleteDetallePedido(Long id) {
        DetallePedido deleteDetallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetallePedido no encontrado"));

        // Eliminar directamente el detalle de pedido de la base de datos
        detallePedidoRepository.delete(deleteDetallePedido);
    }

    /**
     * Obtiene un DetallePedido por su ID
     */
    public DetallePedido getDetallePedido(Long id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetallePedido no encontrado"));
    }

    /**
     * Obtiene todos los DetallePedidos
     */
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidoRepository.findAll();
    }

    /**
     * Actualiza un DetallePedido existente
     */
    public DetallePedido updateDetallePedido(DetallePedido detallePedido) {
        DetallePedido existingDetallePedido = detallePedidoRepository.findById(detallePedido.getIdDetallePedido())
                .orElseThrow(() -> new IllegalArgumentException("DetallePedido no encontrado para actualizar"));
        existingDetallePedido.setCantidadPedido(detallePedido.getCantidadPedido());
        existingDetallePedido.setPrecioPedido(detallePedido.getPrecioPedido());

        return detallePedidoRepository.save(existingDetallePedido);
    }

    /**
     * Obtiene todos los DetallePedidos de un Pedido específico
     */
    public List<DetallePedido> getDetallePedidosByPedido(Long idPedido) {
        return detallePedidoRepository.findByPedidoIdPedido(idPedido);
    }

    /**
     * Obtiene todos los DetallePedidos de un Articulo específico
     */
    public List<DetallePedido> getDetallePedidosByArticulo(Long idArticulo) {
        return detallePedidoRepository.findByArticuloIdArticulo(idArticulo);
    }

    public void deleteDetallesByPedido(Long idPedido) {
        detallePedidoRepository.deleteByPedidoIdPedido(idPedido);
    }
}
