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
    public ResponseEntity<VideojuegoResponseDTO> crear(@Valid @RequestBody VideojuegoRequestDTO videojuego) {
        return ResponseEntity.status(201).body(videojuegoService.guardar(videojuego));
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

    //Buscar por nombre de videojuego
    @GetMapping("/nombre")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(videojuegoService.buscarPorNombre(nombre));
    }

    //Buscar por género de videojuego
    @GetMapping("/genero")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(videojuegoService.buscarPorGenero(genero));
    }

    //Buscar por consola
    @GetMapping("/consola")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorConsola(@RequestParam String consola) {
        return ResponseEntity.ok(videojuegoService.buscarPorConsola(consola));
    }

    //Buscar por precio
    @GetMapping("/precio")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorPrecio(@RequestParam int precio) {
        return ResponseEntity.ok(videojuegoService.buscarPorPrecio(precio));
    }

    //Buscar por modalidad
    @GetMapping("/modalidad")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorModalidad(@RequestParam String modalidad) {
        return ResponseEntity.ok(videojuegoService.buscarPorModalidad(modalidad));
    }

    //Buscar por estado
    @GetMapping("/estado")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(videojuegoService.buscarPorEstado(estado));
    }

    //Buscar por EAN
    @GetMapping("/ean")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorEAN(@RequestParam String ean) {
        return ResponseEntity.ok(videojuegoService.buscarPorEAN(ean));
    }

    //Buscar por precio menor o igual
    @GetMapping("/precio-menor")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorPrecioMenor(@RequestParam int precio) {
        return ResponseEntity.ok(videojuegoService.buscarPorPrecioMenor(precio));
    }

    //Buscar por precio mayor o igual
    @GetMapping("/precio-mayor")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorPrecioMayor(@RequestParam int precio) {
        return ResponseEntity.ok(videojuegoService.buscarPorPrecioMayor(precio));
    }
}
