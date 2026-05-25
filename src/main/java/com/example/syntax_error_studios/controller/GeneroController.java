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

import com.example.syntax_error_studios.model.Genero;
import com.example.syntax_error_studios.service.GeneroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor

public class GeneroController {

    private final GeneroService generoService;

    // GET -> listar todos
    @GetMapping
    public List<Genero> obtenerTodos() {
        return generoService.obtenerTodos();
    }

    // GET -> buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerPorId(@PathVariable Long id) {

        return generoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST -> crear
    @PostMapping
    public ResponseEntity<Genero> guardar(@RequestBody Genero genero) {

        Genero nuevoGenero = generoService.guardar(genero);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoGenero);
    }

    // PUT -> actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Genero> actualizar(
            @PathVariable Long id,
            @RequestBody Genero genero) {

        return generoService.obtenerPorId(id)
                .map(generoExistente -> {

                    genero.setId(id);

                    Genero actualizado = generoService.guardar(genero);

                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE -> eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (generoService.obtenerPorId(id).isPresent()) {

            generoService.eliminar(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
