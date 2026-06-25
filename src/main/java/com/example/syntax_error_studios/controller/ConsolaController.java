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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
// adicionando la documentacion para controlador Consola
@Tag(
    name = "Consolas",
    description = "Operaciones relacionadas con las consolas"
)
@RestController
@RequestMapping("/api/consolas")
@RequiredArgsConstructor


public class ConsolaController {
private final ConsolaService consolaService;

    // GET -> listar todas las consolas + la documentacion 
    @Operation(
        summary = "Obtener todas las consolas",
        description = "Retorna una lista con todas las consolas registradas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista obtenida correctamente"
    )

    @GetMapping
    public List<Consola> obtenerTodas() {
        return consolaService.obtenerTodas();
    }

    // GET -> buscar consola por id+la adicion de su documentacion
    @Operation(
        summary = "Buscar consola por ID",
        description = "Obtiene una consola según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consola encontrada"),
        @ApiResponse(responseCode = "404", description = "Consola no encontrada")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Consola> obtenerPorId(@PathVariable Long id) {

        return consolaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST -> crear consola + la adicion de la operation
    @Operation(
        summary = "Crear consola",
        description = "Registra una nueva consola"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Consola creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })

    @PostMapping
    public ResponseEntity<Consola> guardar(@RequestBody Consola consola) {

        Consola nuevaConsola = consolaService.guardar(consola);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevaConsola);
    }

    // PUT -> actualizar consola + documentacion
    @Operation(
        summary = "Actualizar consola",
        description = "Actualiza la información de una consola existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consola actualizado"),
        @ApiResponse(responseCode = "404", description = "Consola no encontrado")
    })


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

    // DELETE -> eliminar consola + documentacion
    @Operation(
        summary = "Eliminar consola",
        description = "Elimina una consola   según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Consola eliminada"),
        @ApiResponse(responseCode = "404", description = "Consola no encontrada")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (consolaService.obtenerPorId(id).isPresent()) {

            consolaService.eliminar(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
