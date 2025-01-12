package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Categoria;
import com.ipn.mx.domain.Service.CategoriaService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final com.ipn.mx.domain.Repository.CategoriaRepository categoriaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategoria(@RequestBody Categoria categoria) {
        // Create a new categoria
        categoriaService.createCategoria(categoria);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria getCategoria(@PathVariable Long id) {
        // Get categoria by id
        if (id <= 0 || categoriaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Categoria no encontrada");
        }
        return categoriaService.getCategoria(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        // Update an existing categoria
        if (!id.equals(categoria.getIdCategoria())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        Categoria existingCategoria = categoriaRepository.findById(categoria.getIdCategoria()).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        existingCategoria.setNombreCategoria(categoria.getNombreCategoria());
        existingCategoria.setImagenCategoria(categoria.getImagenCategoria());
        existingCategoria.getArticulos().clear();
        if(categoria.getArticulos() != null){
            existingCategoria.getArticulos().addAll(categoria.getArticulos());
        }
        categoriaRepository.save(existingCategoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoria(@PathVariable Long id) {
        if (id <= 0 || categoriaRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Categoria no encontrada");
        }
        // Delete an existing categoria
        categoriaService.deleteCategoria(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> getAllCategorias(){
        return categoriaService.getAllCategorias();
    }
}
