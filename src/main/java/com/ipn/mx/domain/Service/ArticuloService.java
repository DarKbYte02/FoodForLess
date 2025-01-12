package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Articulo;
import com.ipn.mx.domain.Entity.Categoria;
import com.ipn.mx.domain.Repository.ArticuloRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
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
        return articuloRepository.findArticuloWithCategoria(id);
    }

    public List<Articulo> getAllArticulos() {
        // Get all articulos
        return articuloRepository.findAllArticulosWithCategoria();
    }

    public List<Articulo> getArticulosByCategoria(Long idCategoria) {
        // Get all articulos by categoria
        return articuloRepository.findByCategoriaIdCategoria(idCategoria);
    }

}
