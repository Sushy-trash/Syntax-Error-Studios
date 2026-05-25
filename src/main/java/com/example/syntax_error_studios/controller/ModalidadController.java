package com.example.syntax_error_studios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.syntax_error_studios.model.Modalidad;
import com.example.syntax_error_studios.service.ModalidadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/modalidades")
@RequiredArgsConstructor
public class ModalidadController {
    
    private final ModalidadService modalidadService;

    @GetMapping
    public ResponseEntity<List<Modalidad>> obtenerTodos(){
       return ResponseEntity.ok(modalidadService.obtenerTodos()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modalidad> obtenerPorId(@PathVariable Long id){
        return modalidadService.obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modalidad> crear(@Valid @RequestBody Modalidad modalidad){
        Modalidad nuevaModalidad = modalidadService.guardar(modalidad);
        return ResponseEntity.status(201).body(nuevaModalidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modalidad> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody Modalidad datos){
        return modalidadService.obtenerPorId(id)
        .map(existente -> {
            datos.setId(id);
            return ResponseEntity.ok(modalidadService.guardar(datos));
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(modalidadService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        modalidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
