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
    
    //Buscar por género de videojuego
    @Query(
        value = """
                SELECT v.*
                FROM videojuegos v
                INNER JOIN generos g
                    ON v.genero_id = g.id
                WHERE g.nombre LIKE CONCAT('%', :texto, '%')
                """,
                nativeQuery = true
    )
    List <Videojuego> buscarPorGenero(@Param("texto")String texto);

    //Buscar por consola
    @Query(
        value = """
                SELECT v.*
                FROM videojuegos v
                INNER JOIN consolas c
                    ON v.consola_id = c.id
                WHERE c.nombre_consola LIKE CONCAT ('%',:texto,'%')
                """,
                nativeQuery = true
    )
    List <Videojuego> buscarPorConsola(@Param("texto")String texto);

    //Buscar por precio
    @Query(
        value = "SELECT * FROM videojuegos WHERE precio_videojuego = :precio",
        nativeQuery = true
    )
    List <Videojuego> buscarPorPrecio(@Param("precio")int precio);

    //Buscar por modalidad
    @Query(
        value = """
                SELECT v.*
                FROM videojuegos v
                INNER JOIN modalidades m
                    ON v.modalidad_id = m.id
                WHERE m.nombre_modalidad LIKE CONCAT ('%',:texto,'%')
                """,
                nativeQuery = true
    )
    List <Videojuego> buscarPorModalidad(@Param("texto")String texto);

    //Buscar por estado
    @Query(
        value = "SELECT * FROM videojuegos WHERE estado LIKE CONCAT ('%', :texto, '%')",
        nativeQuery = true
    )
    List <Videojuego> buscarPorEstado(@Param("texto")String texto);

    //Buscar por EAN
    @Query(
        value = "SELECT * FROM videojuegos WHERE ean LIKE CONCAT ('%', :texto, '%')",
        nativeQuery = true
    )
    List <Videojuego> buscarPorEan(@Param("texto")String texto);

}
