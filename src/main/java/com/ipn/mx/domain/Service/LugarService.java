package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Lugar;
import com.ipn.mx.domain.Repository.LugarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LugarService {
    private final LugarRepository lugarRepository;

    public void createLugar(Lugar lugar) {
        // Create a lugar
        lugarRepository.save(lugar);
    }

    public void deleteLugar(Long id) {
        // Delete a lugar
        if (id <= 0 || lugarRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Lugar inválido");
        }
        lugarRepository.deleteById(id);
    }

    public Lugar getLugar(Long id) {
        // Get a lugar
        if (id <= 0 || lugarRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Lugar no encontrado");
        }
        return lugarRepository.findById(id).orElse(null);
    }

    public List<Lugar> getLugares() {
        // Get all lugares
        return lugarRepository.findAll();
    }

}
