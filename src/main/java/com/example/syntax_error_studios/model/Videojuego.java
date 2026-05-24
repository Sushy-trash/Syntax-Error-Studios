package com.example.syntax_error_studios.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="El nombre del videojuego es obligatorio")
    @Size(max=100,message="El nombre del videojuego no puede tener mas de 100 carateres")
    @Column(nullable=false,length=100)
    private String nombreVideojuego;

    @NotBlank(message="El codigo EAN del videojuego es obligatorio")
    @Size(max=13,message="El codigo EAN del videojuego no puede tener mas de 13 caracteres")
    @Column(nullable=false,length=13,unique=true)
    private String ean;

    @NotNull(message="La consola para la cual está orientada el videojuego es obligatoria")
    @ManyToOne
    @JoinColumn(name="consola_id",nullable=false)
    private Consola consola;

    @NotNull(message="El genero del videojuego es obligatorio")
    @ManyToOne
    @JoinColumn(name="genero_id",nullable=false)
    private Genero genero;

    @NotNull(message="La modalidad del videojuego es obligatoria")
    @ManyToOne
    @JoinColumn(name="modalidad_id",nullable=false)
    private Modalidad modalidad;

    @NotNull(message="El precio del videojuego es obligatorio")
    @Column(nullable=false)
    @Positive(message="El precio del videojuego debe ser un valor mayor a 0")    
    private int precioVideojuego;
    
    @NotBlank(message="El estado del videojuego es obligatorio")
    @Size(max=50,message="El estado del videojuego no puede tener mas de 50 caracteres")
    @Column(nullable=false,length=50)
    private String estado;
}
