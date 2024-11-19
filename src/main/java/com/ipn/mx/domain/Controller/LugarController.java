package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Lugar;
import com.ipn.mx.domain.Service.LugarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lugar")
public class LugarController {
    private final LugarService lugarService;

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

    @PutMapping
    public void updateLugar(@RequestBody Lugar lugar) {
        // Update a lugar
        lugarService.updateLugar(lugar);
    }

}