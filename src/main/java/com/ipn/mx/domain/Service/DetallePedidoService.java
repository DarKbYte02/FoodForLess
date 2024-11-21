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

    public DetallePedido createDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    /**
     * Elimina un DetallePedido por su ID
     */
    public void deleteDetallePedido(Long id) {
        if (detallePedidoRepository.existsById(id)) {
            detallePedidoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El DetallePedido no existe");
        }
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
        if (detallePedido.getIdDetallePedido() == null || !detallePedidoRepository.existsById(detallePedido.getIdDetallePedido())) {
            throw new IllegalArgumentException("DetallePedido no encontrado para actualizar");
        }
        return detallePedidoRepository.save(detallePedido);
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
