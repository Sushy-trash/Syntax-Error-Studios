package com.example.syntax_error_studios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "modalidades")
public class Modalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="El nombre de la modalidad es obligatorio")
    @Size(max=100,message="El nombre de la modalidad no puede tener mas de 100 carateres")
    @Column(nullable=false,length=100)
    private String nombreModalidad;  
}
