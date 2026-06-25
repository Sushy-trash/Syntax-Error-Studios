package com.example.syntax_error_studios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos necesarios para registrar un videojuego")
public class VideojuegoRequestDTO {
    @Schema(
        description = "Nombre del videojuego",
        example = "The Legend of Zelda"
    )
    @NotBlank(message="El nombre del videojuego es obligatorio")
    private String nombreVideojuego;

    @Schema(
        description = "El codigo EAN del videojuego",
        example = "1252987218115"
    )
    @NotBlank(message="El codigo EAN del videojuego es obligatorio")    
    private String ean;

    @Schema(
        description = "El precio del videojuego",
        example = "$59.990"
    )
    @NotNull(message="El precio del videojuego es obligatorio") 
    @Positive(message="El precio del videojuego debe ser un valor mayor a 0")   
    private int precioVideojuego;


    @Schema(
        description = "El estado del videojuego",
        example = "Nuevo-Usado"
    )
    @NotBlank(message="El estado del videojuego es obligatorio")
    private String estado;

    //Ids de las entidades/clases relacionadas
    @NotNull(message="La consola para la cual está orientada el videojuego es obligatoria")
    private Long consolaId;
    @NotNull(message="El genero del videojuego es obligatorio")
    private Long generoId;
    @NotNull(message="La modalidad del videojuego es obligatoria")  
    private Long modalidadId;
}
