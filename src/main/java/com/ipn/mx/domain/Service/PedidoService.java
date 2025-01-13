package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ArticuloService articuloService;

    public void createPedido(Pedido pedido) {
        // Create a pedido
        pedidoRepository.save(pedido);
    }

    public void deletePedido(Long id) {
        // Delete a pedido
        pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado para eliminar"));
        pedidoRepository.deleteById(id);
    }

    public void updatePedido(Pedido pedido) {
        // Update a pedido
        if (pedido.getIdPedido() == null) {
            throw new IllegalArgumentException("El ID del cuerpo no puede ser nulo");
        }
        Pedido existingPedido = pedidoRepository.findById(pedido.getIdPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
        existingPedido.setTotalPedido(pedido.getTotalPedido());
        existingPedido.setEstadoPedido(pedido.getEstadoPedido());
        existingPedido.getDetallePedidos().clear();
        existingPedido.getReviews().clear();
        if (pedido.getDetallePedidos() != null) {
            existingPedido.getDetallePedidos().addAll(pedido.getDetallePedidos());
        }
        if (pedido.getReviews() != null) {
            existingPedido.getReviews().addAll(pedido.getReviews());
        }
        pedidoRepository.save(existingPedido);
    }

    public Pedido getPedido(Long id) {
        // Get a pedido
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));
    }

    public List<Pedido> getPedidos() {
        // Get all pedidos
        return pedidoRepository.findAll();
    }

}
