package com.example.syntax_error_studios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.syntax_error_studios.model.Genero;
import com.example.syntax_error_studios.repository.GeneroRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class GeneroService {
        private final GeneroRepository generoRepository;

    // Obtener todos los géneros
    public List<Genero> obtenerTodos() {
        return generoRepository.findAll();
    }

    // Obtener género por ID
    public Optional<Genero> obtenerPorId(Long id) {
        return generoRepository.findById(id);
    }

    // Guardar género
    public Genero guardar(Genero genero) {
        return generoRepository.save(genero);
    }

    // Eliminar género
    public void eliminar(Long id) {
        generoRepository.deleteById(id);
    }
}
