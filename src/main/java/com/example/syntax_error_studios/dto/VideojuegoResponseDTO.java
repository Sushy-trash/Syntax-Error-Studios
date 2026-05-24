package com.example.syntax_error_studios.dto;

import java.math.BigDecimal;

import com.example.syntax_error_studios.model.Consola;
import com.example.syntax_error_studios.model.Genero;
import com.example.syntax_error_studios.model.Modalidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class VideojuegoResponseDTO {
    private Long id;
    private String nombreVideojuego;
    private String ean;
    private BigDecimal precioVideojuego;
    private String estado;

    //llamado de consola
    private  String nombreConsola;
    //llamado de el nombre del genero
    private String nombreGenero;
    //llamado de la modalidad
    private String nombreModalidad;

}
