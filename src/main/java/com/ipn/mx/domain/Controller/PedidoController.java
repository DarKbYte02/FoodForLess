package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<String> createPedido(@RequestBody Pedido pedido) {
        // Create a new pedido
        pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido creado");
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

    @GetMapping
    public List<Pedido> getPedidos(){
        return pedidoService.getPedidos();
    }

    @GetMapping("/articulo/{idArticulo}")
    // Get all pedidos by articulo
    public List<Pedido> getPedidosByArticulo(@PathVariable Long idArticulo){
        return pedidoService.getPedidosByArticulo(idArticulo);
    }
}
