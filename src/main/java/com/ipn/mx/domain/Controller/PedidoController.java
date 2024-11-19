package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public void createPedido(@RequestBody Pedido pedido) {
        // Create a new pedido
        pedidoService.createPedido(pedido);
    }

    @GetMapping("/{id}")
    public Pedido getPedido(@PathVariable Long id) {
        // Get pedido by id
        return pedidoService.getPedido(id);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        // Delete an existing pedido
        pedidoService.deletePedido(id);
    }

    @PutMapping
    public void updatePedido(@RequestBody Pedido pedido) {
        // Update an existing pedido
        pedidoService.updatePedido(pedido);
    }

    @GetMapping("/total")
    public List<Pedido> getPedidos(){
        return pedidoService.getPedidos();
    }

}
