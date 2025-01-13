package com.ipn.mx.domain.Controller;


import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.Categoria;
import com.ipn.mx.domain.Repository.ArticuloRepository;
import com.ipn.mx.domain.Service.ArticuloService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/articulo")
public class ArticuloController{
    private final ArticuloService articuloService;
    private final ArticuloRepository articuloRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticulo(@RequestBody Articulo articulo) {
        // Create a new articulo
        articuloService.createArticulo(articulo);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Articulo getArticulo(@PathVariable Long id) {
        // Get articulo by id
        if (id<=0 || articuloRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Articulo no encontrado");
        }
        return articuloService.getArticulo(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
        // Update an existing articulo
        if (!id.equals(articulo.getIdArticulo())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        try {
            Articulo existingArticulo = articuloRepository.findById(articulo.getIdArticulo()).orElseThrow(() -> new EntityNotFoundException("Articulo no encontrado"));
            existingArticulo.setNombreArticulo(articulo.getNombreArticulo());
            existingArticulo.setDescripcionArticulo(articulo.getDescripcionArticulo());
            existingArticulo.setPrecioArticulo(articulo.getPrecioArticulo());
            existingArticulo.setImagenArticulo(articulo.getImagenArticulo());
            existingArticulo.setCategoria(articulo.getCategoria().getIdCategoria());
            existingArticulo.setStock(articulo.getStock());
            existingArticulo.setTiempoInicial(articulo.getTiempoInicial());
            existingArticulo.setTiempoFinal(articulo.getTiempoFinal());
            existingArticulo.setLugar(articulo.getLugar().getIdLugar());

            if(articulo.getDetallePedidos() != null){
                existingArticulo.getDetallePedidos().clear();
                existingArticulo.getDetallePedidos().addAll(articulo.getDetallePedidos());
            }

            articuloRepository.save(existingArticulo);

            //articuloService.updateArticulo(articulo);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Articulo no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticulo(@PathVariable Long id) {
        if (id<=0 || articuloRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Articulo no encontrado");
        }
        // Delete an existing articulo
        try {
            articuloService.deleteArticulo(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Articulo no encontrado");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Articulo> getAllArticulos() {
        return articuloService.getAllArticulos();
    }

    @GetMapping("/categoria/{idCategoria}")
    @ResponseStatus(HttpStatus.OK)
    public List<Articulo> getArticulosByCategoria(@PathVariable Long idCategoria) {
        // Get all articulos by categoria
        return articuloService.getArticulosByCategoria(idCategoria);
    }
}
