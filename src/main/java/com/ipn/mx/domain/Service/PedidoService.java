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
        pedidoRepository.deleteById(id);
    }

    public void updatePedido(Pedido pedido) {
        // Update a pedido
        pedidoRepository.save(pedido);
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
