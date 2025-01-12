package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.DetallePedido;
import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Repository.ArticuloRepository;
import com.ipn.mx.domain.Repository.DetallePedidoRepository;
import com.ipn.mx.domain.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ArticuloRepository articuloRepository;

    public DetallePedido createDetallePedido(DetallePedido detallePedido) {
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
//        existingDetallePedido.setCantidadPedido(detallePedido.getCantidadPedido());
//        existingDetallePedido.setPrecioPedido(detallePedido.getPrecioPedido());
//        // Eliminar solo el detalle específico de las listas, no limpiar todas
//        existingDetallePedido.getPedido().getDetallePedidos().remove(detallePedido);
//        existingDetallePedido.getArticulo().getDetallePedidos().remove(detallePedido);
//        // Actualizar el detalle de pedido con los nuevos valores
//        if (detallePedido.getPedido() != null) {
//            existingDetallePedido.setPedido(detallePedido.getPedido());
//        }
//        if (detallePedido.getArticulo() != null) {
//            existingDetallePedido.setArticulo(detallePedido.);
//        }
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
