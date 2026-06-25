package com.example.syntax_error_studios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.syntax_error_studios.model.Modalidad;
import com.example.syntax_error_studios.service.ModalidadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//documentacion para controlador de Modalidad
@Tag(
    name = "Modalidades",
    description = "Operaciones relacionadas con las modalidades de videojuegos"
)
@RestController
@RequestMapping("/api/modalidades")
@RequiredArgsConstructor
public class ModalidadController {
    
    private final ModalidadService modalidadService;
    //obtener todos + documentacion
    @Operation(
        summary = "Obtener todas las modalidades",
        description = "Retorna una lista con todas las modalidades registradas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista obtenida correctamente"
    )


    @GetMapping
    public ResponseEntity<List<Modalidad>> obtenerTodos(){
       return ResponseEntity.ok(modalidadService.obtenerTodos()); 
    }
    
    
    //obtencion por id + documentacion
    @Operation(
        summary = "Buscar modalidad por ID",
        description = "Obtiene una modalidad según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modalidad encontrada"),
        @ApiResponse(responseCode = "404", description = "Modalidad no encontrada")
    })


    @GetMapping("/{id}")
    public ResponseEntity<Modalidad> obtenerPorId(@PathVariable Long id){
        return modalidadService.obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    //crear -> agregar + documentacion

    @Operation(
        summary = "Crear modalidad",
        description = "Registra una nueva modalidad"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Modalidad creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })


    @PostMapping
    public ResponseEntity<Modalidad> crear(@Valid @RequestBody Modalidad modalidad){
        Modalidad nuevaModalidad = modalidadService.guardar(modalidad);
        return ResponseEntity.status(201).body(nuevaModalidad);
    }
    //crear -> actualizar + documentacion

    @Operation(
        summary = "Actualizar modalidad",
        description = "Actualiza una modalidad existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modalidad actualizada"),
        @ApiResponse(responseCode = "404", description = "Modalidad no encontrada")
    })


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
    //ELIMINAR + DOCUMENTACION
    @Operation(
        summary = "Eliminar modalidad",
        description = "Elimina una modalidad según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Modalidad eliminada"),
        @ApiResponse(responseCode = "404", description = "Modalidad no encontrada")
    })
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(modalidadService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        modalidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
