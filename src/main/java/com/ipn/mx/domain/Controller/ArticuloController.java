package com.ipn.mx.domain.Controller;


import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.Categoria;
import com.ipn.mx.domain.Service.ArticuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/articulo")
public class ArticuloController{
    private final ArticuloService articuloService;

    @PostMapping
    public void createArticulo(@RequestBody Articulo articulo) {
        // Create a new articulo
        articuloService.createArticulo(articulo);
    }
    @GetMapping("/{id}")
    public Articulo getArticulo(@PathVariable Long id) {
        // Get articulo by id
        return articuloService.getArticulo(id);
    }

    @PutMapping("/{id}")
    public void updateArticulo(@PathVariable Long id, @RequestBody Articulo articulo) {
        // Update an existing articulo
        if (!id.equals(articulo.getIdArticulo())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        articuloService.updateArticulo(articulo);
    }

    @DeleteMapping("/{id}")
    public void deleteArticulo(@PathVariable Long id) {
        // Delete an existing articulo
        articuloService.deleteArticulo(id);
    }

    @GetMapping
    public List<Articulo> getAllArticulos(){
        return articuloService.getAllArticulos();
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Articulo> getArticulosByCategoria(@PathVariable Long idCategoria) {
        // Get all articulos by categoria
        return articuloService.getArticulosByCategoria(idCategoria);
    }
}
