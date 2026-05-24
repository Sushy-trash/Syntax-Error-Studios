package com.example.syntax_error_studios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.syntax_error_studios.model.Videojuego;
import com.example.syntax_error_studios.repository.VideojuegoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideojuegoService {
    
    private final VideojuegoRepository videojuegoRepository;

    //Obtener todos los videojuegos
    public List<Videojuego> obtenerTodos(){
        return videojuegoRepository.findAll();
    }

    //Obtener videojuego por ID
    public Optional<Videojuego> obtenerPorId(Long id){
        return videojuegoRepository.findById(id);
    }

    //Guardar videojuego
    public Videojuego guardar(Videojuego videojuego){
        return videojuegoRepository.save(videojuego);
    }

    //Eliminar videojuego
    public void eliminar(Long id){
        videojuegoRepository.deleteById(id);
    }

    
}
