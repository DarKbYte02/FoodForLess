package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Lugar;
import com.ipn.mx.domain.Service.LugarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lugar")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class LugarController {
    private final LugarService lugarService;
    private final com.ipn.mx.domain.Repository.LugarRepository lugarRepository;

    @PostMapping
    public void createLugar(@RequestBody Lugar lugar) {
        // Create a new lugar
        lugarService.createLugar(lugar);
    }

    @GetMapping("/{id}")
    public Lugar getLugar(@PathVariable Long id) {
        // Get lugar by id
        return lugarService.getLugar(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLugar(@PathVariable Long id) {
        // Delete an existing lugar
        lugarService.deleteLugar(id);
    }

    @GetMapping
    public List<Lugar> getLugares(){
        return lugarService.getLugares();
    }

    @PutMapping("/{id}")
    public void updateLugar(@PathVariable Long id, @RequestBody Lugar lugar) {
        // Update a lugar
        if (!id.equals(lugar.getIdLugar())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        Lugar existingLugar = lugarRepository.findById(lugar.getIdLugar()).orElseThrow(() -> new EntityNotFoundException("Lugar no encontrada"));
        existingLugar.setNombreLugar(lugar.getNombreLugar());
        existingLugar.setDescripcionLugar(lugar.getDescripcionLugar());
        existingLugar.setImagenLugar(lugar.getImagenLugar());
        existingLugar.setLatitudLugar(lugar.getLatitudLugar());
        existingLugar.setLongitudLugar(lugar.getLongitudLugar());
        existingLugar.setDireccionLugar(lugar.getDireccionLugar());
        existingLugar.setHoraApertura(lugar.getHoraApertura());
        existingLugar.setHoraCierre(lugar.getHoraCierre());
        existingLugar.setCalificacionTotal(lugar.getCalificacionTotal());
        existingLugar.setUser(lugar.getUser().getIdUser());
        if(lugar.getArticulos() != null){
            existingLugar.getArticulos().clear();
            existingLugar.getArticulos().addAll(lugar.getArticulos());
        }
        if(lugar.getReviews() != null){
            existingLugar.getReviews().clear();
            existingLugar.getReviews().addAll(lugar.getReviews());
        }
        if(lugar.getPedidos() != null){
            existingLugar.getPedidos().clear();
            existingLugar.getPedidos().addAll(lugar.getPedidos());
        }
        lugarRepository.save(existingLugar);
       // lugarService.updateLugar(lugar);
    }

}
