package com.example.syntax_error_studios.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="consolas")
public class Consola {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank(message="El nombre de la consola es obligatorio")
    @Column(nullable = false,length = 100)
    private String nombreConsola;

    @NotBlank(message="EL codigo SKU de la consola es obligatorio")
    @Column(nullable=false,length=100,unique =true)
    private String SKU;

}
