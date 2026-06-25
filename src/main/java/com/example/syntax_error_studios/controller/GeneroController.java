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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

//adicion de documentacion de tag para controlador Genero
@Tag(
    name = "Géneros",
    description = "Operaciones relacionadas con géneros"
)
@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor

public class GeneroController {

    private final GeneroService generoService;

    // GET -> listar todos+ adicion de documentacion
    @Operation(
        summary = "Obtener todas los generos",
        description = "Retorna una lista con todas los generos registrados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista obtenida correctamente"
    )

    @GetMapping
    public List<Genero> obtenerTodos() {
        return generoService.obtenerTodos();
    }

    // GET -> buscar por id + documentacion
    @Operation(
        summary = "Buscar género por ID",
        description = "Obtiene un género según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Género encontrado"),
        @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })


    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerPorId(@PathVariable Long id) {

        return generoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST -> crear + documentacion
    @Operation(
        summary = "Crear género",
        description = "Registra un nuevo género"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Género creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })

    @PostMapping
    public ResponseEntity<Genero> guardar(@RequestBody Genero genero) {

        Genero nuevoGenero = generoService.guardar(genero);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoGenero);
    }

    // PUT -> actualizar + documentacion
    @Operation(
        summary = "Actualizar género",
        description = "Actualiza la información de un género existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Género actualizado"),
        @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })

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

    // DELETE -> eliminar + documentacion
    @Operation(
        summary = "Eliminar género",
        description = "Elimina un género según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Género eliminado"),
        @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (generoService.obtenerPorId(id).isPresent()) {

            generoService.eliminar(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
