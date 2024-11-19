package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.DetallePedido;
import com.ipn.mx.domain.Service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/detallePedido")
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    @PostMapping
    public void createDetallePedido(@RequestBody DetallePedido detallePedido) {
        // Create a new detallePedido
        detallePedidoService.createDetallePedido(detallePedido);
    }

    @GetMapping("/{id}")
    public DetallePedido getDetallePedido(@PathVariable Long id) {
        // Get detallePedido by id
        return detallePedidoService.getDetallePedido(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDetallePedido(@PathVariable Long id) {
        // Delete an existing detallePedido
        detallePedidoService.deleteDetallePedido(id);
    }

    @GetMapping
    public List<DetallePedido> getDetallePedidos(){
        return detallePedidoService.getDetallePedidos();
    }

    @PutMapping
    public DetallePedido updateDetallePedido(@RequestBody DetallePedido detallePedido) {
        // Update a detallePedido
        return detallePedidoService.updateDetallePedido(detallePedido);
    }
}
