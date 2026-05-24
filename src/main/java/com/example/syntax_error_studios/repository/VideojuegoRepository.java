package com.example.syntax_error_studios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.syntax_error_studios.model.Videojuego;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    //Buscar por nombre de videojuego
    @Query(
        value = "SELECT * FROM videojuegos WHERE nombre_videojuego LIKE CONCAT ('%', :texto, '%')",
        nativeQuery = true
    )
    List <Videojuego> buscarPorNombre(@Param("texto")String texto);
    

}
