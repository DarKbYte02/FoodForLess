package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.DetallePedido;
import com.ipn.mx.domain.Service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/detallePedido", produces = MediaType.APPLICATION_JSON_VALUE)
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;
    private final com.ipn.mx.domain.Repository.DetallePedidoRepository detallePedidoRepository;

    @PostMapping
    public ResponseEntity<DetallePedido> createDetallePedido(@RequestBody DetallePedido detallePedido) {
        return new ResponseEntity<>(detallePedidoService.createDetallePedido(detallePedido), HttpStatus.CREATED);
    }

    // Obtener un DetallePedido por su ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DetallePedido getDetallePedido(@PathVariable Long id) {
        return detallePedidoService.getDetallePedido(id);
    }

    // Eliminar un DetallePedido por su ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDetallePedido(@PathVariable Long id) {
        detallePedidoService.deleteDetallePedido(id);
    }

    // Obtener todos los DetallePedidos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidoService.getDetallePedidos();
    }

    // Actualizar un DetallePedido existente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DetallePedido updateDetallePedido(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {

        if (!id.equals(detallePedido.getIdDetallePedido())) {
            System.out.println(detallePedido.getIdDetallePedido());
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden"+ id + " " + detallePedido);
        }
        DetallePedido existingDetallePedido = detallePedidoService.getDetallePedido(id);
        existingDetallePedido.setIdDetallePedido(detallePedido.getIdDetallePedido());
        existingDetallePedido.setCantidadPedido(detallePedido.getCantidadPedido());
        existingDetallePedido.setPrecioPedido(detallePedido.getPrecioPedido());
        existingDetallePedido.setPedido(detallePedido.getPedido().getIdPedido());
        existingDetallePedido.setArticulo(detallePedido.getArticulo().getIdArticulo());
        DetallePedido updatedDetallePedido = detallePedidoRepository.save(existingDetallePedido);
        //DetallePedido updatedDetallePedido = detallePedidoService.updateDetallePedido(detallePedido);
        return updatedDetallePedido;
    }

    // Obtener todos los DetallePedidos de un Pedido específico
    @GetMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public List<DetallePedido> getDetallePedidosByPedido(@PathVariable Long idPedido) {
        return detallePedidoService.getDetallePedidosByPedido(idPedido);
    }

    // Obtener todos los DetallePedidos de un Articulo específico
    @GetMapping("/articulo/{idArticulo}")
    @ResponseStatus(HttpStatus.OK)
    public List<DetallePedido> getDetallePedidosByArticulo(@PathVariable Long idArticulo) {
        return detallePedidoService.getDetallePedidosByArticulo(idArticulo);
    }

    @DeleteMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDetallesByPedido(@PathVariable Long idPedido) {
        detallePedidoService.deleteDetallesByPedido(idPedido);
        return ResponseEntity.noContent().build();
    }
}
