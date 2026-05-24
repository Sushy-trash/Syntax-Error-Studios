package com.example.syntax_error_studios.model;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name= "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message="El nombre del genero es obligatorio")
    @Size(max=100,message="El nombre del genero no puede tener mas de 100 carateres")
    @Column(nullable=false,length=100)
    private String nombre;
}
