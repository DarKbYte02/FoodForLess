package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Pedido;
import com.ipn.mx.domain.Service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;
    private final com.ipn.mx.domain.Repository.PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<String> createPedido(@RequestBody Pedido pedido) {
        // Create a new pedido
        pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido creado");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido getPedido(@PathVariable Long id) {
        // Get pedido by id
        return pedidoService.getPedido(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePedido(@PathVariable Long id) {
        // Delete an existing pedido
        pedidoService.deletePedido(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePedido(@PathVariable Long id,@RequestBody Pedido pedido) {
        // Update an existing pedido
        if (!id.equals(pedido.getIdPedido())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        Pedido existingPedido = pedidoService.getPedido(id);
        existingPedido.setEstadoPedido(pedido.getEstadoPedido());
        existingPedido.setTotalPedido(pedido.getTotalPedido());
        if(pedido.getDetallePedidos() != null){
            existingPedido.setDetallePedidos(pedido.getDetallePedidos());
        }
        existingPedido.setLugar(pedido.getLugar().getIdLugar());
        existingPedido.setUser(pedido.getUser().getIdUser());
        if(pedido.getReviews() != null){
            existingPedido.setReviews(pedido.getReviews());
        }
        pedidoRepository.save(existingPedido);
        //pedidoService.updatePedido(pedido);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> getPedidos(){
        List<Pedido> pedidos = pedidoService.getPedidos();
        pedidos.forEach(pedido -> Hibernate.initialize(pedido.getUser()));
        return pedidos;
    }

}
