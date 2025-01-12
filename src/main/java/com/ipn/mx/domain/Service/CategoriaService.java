package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Categoria;
import com.ipn.mx.domain.Repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public void createCategoria(Categoria categoria) {
        // Create a new categoria
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
