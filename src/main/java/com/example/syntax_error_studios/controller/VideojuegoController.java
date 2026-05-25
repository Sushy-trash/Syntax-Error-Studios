package com.example.syntax_error_studios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.syntax_error_studios.dto.VideojuegoRequestDTO;
import com.example.syntax_error_studios.dto.VideojuegoResponseDTO;
import com.example.syntax_error_studios.service.VideojuegoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    @GetMapping
    public ResponseEntity<List<VideojuegoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return videojuegoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VideojuegoResponseDTO> crear(@Valid @RequestBody VideojuegoResponseDTO videojuego) {
        return ResponseEntity.status(201).body(videojuego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VideojuegoRequestDTO datos) {
        return videojuegoService.obtenerPorId(id)
                .map(existente -> {
                    return ResponseEntity.ok(videojuegoService.guardar(datos));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(videojuegoService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        videojuegoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
