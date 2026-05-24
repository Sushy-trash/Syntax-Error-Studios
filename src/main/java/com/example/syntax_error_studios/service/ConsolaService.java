package com.example.syntax_error_studios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.syntax_error_studios.model.Consola;
import com.example.syntax_error_studios.repository.ConsolaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsolaService {
        private final ConsolaRepository consolaRepository;

    public List<Consola> obtenerTodas() {
        return consolaRepository.findAll();
    }

    public Optional<Consola> obtenerPorId(Long id) {
        return consolaRepository.findById(id);
    }

    public Consola guardar(Consola consola) {
        return consolaRepository.save(consola);
    }

    public void eliminar(Long id) {
        consolaRepository.deleteById(id);
    }

}
