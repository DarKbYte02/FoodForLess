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
        lugarRepository.deleteById(id);
    }

    public Lugar getLugar(Long id) {
        // Get a lugar
        return lugarRepository.findById(id).orElse(null);
    }

    public List<Lugar> getLugares() {
        // Get all lugares
        return lugarRepository.findAll();
    }

    public void updateLugar(Lugar lugar) {
        // Update a lugar
        lugarRepository.save(lugar);
    }

}
