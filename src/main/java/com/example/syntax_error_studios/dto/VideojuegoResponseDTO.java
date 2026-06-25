package com.example.syntax_error_studios.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "Respuesta con la información de un videojuego")
public class VideojuegoResponseDTO {
    private Long id;
    private String nombreVideojuego;
    private String ean;
    private int precioVideojuego;
    private String estado;

    //llamado de consola solo nombre de la consola
    private  String nombreConsola;
    //llamado de genero, solo el nombre del genero
    private String nombreGenero;
    //llamado de la modalidad pero solo el e nombre de la modalidad
    private String nombreModalidad;

}
