package com.example.syntax_error_studios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.syntax_error_studios.dto.VideojuegoRequestDTO;
import com.example.syntax_error_studios.dto.VideojuegoResponseDTO;
import com.example.syntax_error_studios.service.VideojuegoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


//documentacion para controlador de VideoJuego
@Tag(
    name = "Videojuegos",
    description = "Administración y búsqueda de videojuegos"
)
@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoService videojuegoService;
    //Obtener todos los videojuegos + documentacion 
    @Operation(
        summary = "Obtener todos los videojuegos",
        description = "Retorna una lista de todos los videojuegos registrados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista obtenida correctamente"
    )


    @GetMapping
    public ResponseEntity<List<VideojuegoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodos());
    }
    //obtener por id + documentacion
    @Operation(
        summary = "Buscar videojuego por ID",
        description = "Obtiene un videojuego según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Videojuego encontrado"),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })

    
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return videojuegoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //crear videojuego + documentacion
    @Operation(
        summary = "Crear videojuego",
        description = "Registra un nuevo videojuego"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Videojuego creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })


    @PostMapping
    public ResponseEntity<VideojuegoResponseDTO> crear(@Valid @RequestBody VideojuegoRequestDTO videojuego) {
        return ResponseEntity.status(201).body(videojuegoService.guardar(videojuego));
    }
    //agregar -> actualizar
    @Operation(
        summary = "Actualizar videojuego",
        description = "Actualiza la información de un videojuego existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Videojuego actualizado"),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })


    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VideojuegoRequestDTO datos) {
        return videojuegoService.obtenerPorId(id)
                .map(existente -> {
                    return ResponseEntity.ok(videojuegoService.actualizar(id, datos));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    // eliminar + documentacion
    @Operation(
        summary = "Eliminar videojuego",
        description = "Elimina un videojuego según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Videojuego eliminado"),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(videojuegoService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        videojuegoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //Buscar por nombre de videojuego + documentacion
    @Operation(
        summary = "Buscar videojuegos por nombre",
        description = "Obtiene videojuegos cuyo nombre coincida con el valor ingresado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/nombre")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(videojuegoService.buscarPorNombre(nombre));
    }

    //Buscar por género de videojuego+ documentacion
    @Operation(
        summary = "Buscar videojuegos por género",
        description = "Obtiene videojuegos asociados a un género específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/genero")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(videojuegoService.buscarPorGenero(genero));
    }


    //Buscar por consola+documentacion
    @Operation(
        summary = "Buscar videojuegos por consola",
        description = "Obtiene videojuegos asociados a una consola específica"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )


    @GetMapping("/consola")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorConsola(@RequestParam String consola) {
        return ResponseEntity.ok(videojuegoService.buscarPorConsola(consola));
    }

    //Buscar por precio+documentacion
    @Operation(
        summary = "Buscar videojuegos por precio exacto",
        description = "Obtiene videojuegos con el precio indicado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/precio")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorPrecio(@RequestParam int precio) {
        return ResponseEntity.ok(videojuegoService.buscarPorPrecio(precio));
    }

    //Buscar por modalidad+documentacion
    @Operation(
        summary = "Buscar videojuegos por modalidad",
        description = "Obtiene videojuegos según su modalidad"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )


    @GetMapping("/modalidad")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorModalidad(@RequestParam String modalidad) {
        return ResponseEntity.ok(videojuegoService.buscarPorModalidad(modalidad));
    }

    //Buscar por estado + documentacion
    @Operation(
        summary = "Buscar videojuegos por estado",
        description = "Obtiene videojuegos según su estado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/estado")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(videojuegoService.buscarPorEstado(estado));
    }

    //Buscar por EAN + documentacion
    @Operation(
        summary = "Buscar videojuegos por EAN",
        description = "Obtiene videojuegos según el código EAN"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/ean")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorEAN(@RequestParam String ean) {
        return ResponseEntity.ok(videojuegoService.buscarPorEAN(ean));
    }

    //Buscar por precio menor o igual + documentacion
    @Operation(
        summary = "Buscar videojuegos con precio menor o igual",
        description = "Obtiene videojuegos cuyo precio es menor o igual al valor indicado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/precio-menor")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorPrecioMenor(@RequestParam int precio) {
        return ResponseEntity.ok(videojuegoService.buscarPorPrecioMenor(precio));
    }


    //Buscar por precio mayor o igual + documentacion
    @Operation(
        summary = "Buscar videojuegos con precio mayor o igual",
        description = "Obtiene videojuegos cuyo precio es mayor o igual al valor indicado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Búsqueda realizada correctamente"
    )

    @GetMapping("/precio-mayor")
    public ResponseEntity<List<VideojuegoResponseDTO>> buscarPorPrecioMayor(@RequestParam int precio) {
        return ResponseEntity.ok(videojuegoService.buscarPorPrecioMayor(precio));
    }
}
