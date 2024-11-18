package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Repository.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticuloService {
    private final ArticuloRepository articuloRepository;

    public void createArticulo(Articulo articulo) {
        // Create a new articulo
        articuloRepository.save(articulo);
    }

    public void updateArticulo(Articulo articulo) {
        // Update an existing articulo
        if(articulo.getIdArticulo() == null) {
            throw new IllegalArgumentException("El ID del cuerpo no puede ser nulo");
        }
        articuloRepository.save(articulo);
    }

    public void deleteArticulo(Long id) {
        // Delete an existing articulo
        articuloRepository.deleteById(id);
    }

    public Articulo getArticulo(Long id) {
        // Get articulo by id
        return articuloRepository.findById(id).orElse(null);
    }

}
