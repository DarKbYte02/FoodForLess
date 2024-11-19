package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Categoria;
import com.ipn.mx.domain.Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public void createCategoria(Categoria categoria) {
        // Create a new categoria
        categoriaRepository.save(categoria);
    }

    public void updateCategoria(Categoria categoria) {
        // Update an existing categoria
        if(categoria.getIdCategoria() == null) {
            throw new IllegalArgumentException("El ID del cuerpo no puede ser nulo");
        }
        categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        // Delete an existing categoria
        categoriaRepository.deleteById(id);
    }

    public Categoria getCategoria(Long id) {
        // Get categoria by id
        return categoriaRepository.findById(id).orElse(null);
    }

    public List<Categoria> getAllCategorias() {
        // Get all categorias
        return categoriaRepository.findAll();
    }
}
