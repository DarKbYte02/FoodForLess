package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

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
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> getPedidos() {
        // Get all pedidos
        return pedidoRepository.findAll();
    }
}
