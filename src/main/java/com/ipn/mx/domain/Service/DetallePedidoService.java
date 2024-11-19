package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.DetallePedido;
import com.ipn.mx.domain.Repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;

    public void createDetallePedido(DetallePedido detallePedido) {
        // Create a detallePedido
        detallePedidoRepository.save(detallePedido);
    }

    public void deleteDetallePedido(Long id) {
        // Delete a detallePedido
        detallePedidoRepository.deleteById(id);
    }

    public DetallePedido getDetallePedido(Long id) {
        // Get a detallePedido
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public List<DetallePedido> getDetallePedidos() {
        // Get all detallePedidos
        return detallePedidoRepository.findAll();
    }

    public DetallePedido updateDetallePedido(DetallePedido detallePedido) {
        // Update a detallePedido
        return detallePedidoRepository.save(detallePedido);
    }
}
