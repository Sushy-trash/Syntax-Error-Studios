package com.example.syntax_error_studios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.syntax_error_studios.model.Modalidad;
import com.example.syntax_error_studios.repository.ModalidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModalidadService {
    
    private final ModalidadRepository modalidadRepository;

    //Obtener todas las modalidades
    public List<Modalidad> obtenerTodos() {
        return modalidadRepository.findAll();
    }

    // Obtener modalidad por ID
    public Optional<Modalidad> obtenerPorId(Long id) {
        return modalidadRepository.findById(id);
    }

    // Guardar modalidad
    public Modalidad guardar(Modalidad modalidad) {
        return modalidadRepository.save(modalidad);
    }

    // Eliminar modalidad
    public void eliminar(Long id) {
        modalidadRepository.deleteById(id);
    }

}
