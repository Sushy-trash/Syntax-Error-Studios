package com.example.syntax_error_studios.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.syntax_error_studios.model.Consola;
import com.example.syntax_error_studios.service.ConsolaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consolas")
@RequiredArgsConstructor

public class ConsolaController {
private final ConsolaService consolaService;

    // GET -> listar todas las consolas
    @GetMapping
    public List<Consola> obtenerTodas() {
        return consolaService.obtenerTodas();
    }

    // GET -> buscar consola por id
    @GetMapping("/{id}")
    public ResponseEntity<Consola> obtenerPorId(@PathVariable Long id) {

        return consolaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST -> crear consola
    @PostMapping
    public ResponseEntity<Consola> guardar(@RequestBody Consola consola) {

        Consola nuevaConsola = consolaService.guardar(consola);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevaConsola);
    }

    // PUT -> actualizar consola
    @PutMapping("/{id}")
    public ResponseEntity<Consola> actualizar(
            @PathVariable Long id,
            @RequestBody Consola consola) {

        return consolaService.obtenerPorId(id)
                .map(consolaExistente -> {

                    consola.setId(id);

                    Consola actualizada = consolaService.guardar(consola);

                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE -> eliminar consola
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (consolaService.obtenerPorId(id).isPresent()) {

            consolaService.eliminar(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
