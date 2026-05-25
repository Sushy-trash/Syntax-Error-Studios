package com.example.syntax_error_studios.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.syntax_error_studios.dto.VideojuegoRequestDTO;
import com.example.syntax_error_studios.dto.VideojuegoResponseDTO;
import com.example.syntax_error_studios.model.Consola;
import com.example.syntax_error_studios.model.Genero;
import com.example.syntax_error_studios.model.Modalidad;
import com.example.syntax_error_studios.model.Videojuego;
import com.example.syntax_error_studios.repository.ConsolaRepository;
import com.example.syntax_error_studios.repository.GeneroRepository;
import com.example.syntax_error_studios.repository.ModalidadRepository;
import com.example.syntax_error_studios.repository.VideojuegoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideojuegoService {
    
    private final VideojuegoRepository videojuegoRepository;
    private final GeneroRepository generoRepository;
    private final ConsolaRepository consolaRepository;
    private final ModalidadRepository modalidadRepository;

    //Mapeo
    private VideojuegoResponseDTO mapToDTO(Videojuego videojuego){

        VideojuegoResponseDTO dto = new VideojuegoResponseDTO();

        dto.setId(videojuego.getId());
        dto.setNombreVideojuego(videojuego.getNombreVideojuego());
        dto.setEan(videojuego.getEan());

        dto.setNombreConsola(
            videojuego.getConsola().getNombreConsola()
        );

        dto.setNombreGenero(
            videojuego.getGenero().getNombreGenero()
        );

        dto.setNombreModalidad(
            videojuego.getModalidad().getNombreModalidad()
        );
        dto.setPrecioVideojuego(videojuego.getPrecioVideojuego());
        dto.setEstado(videojuego.getEstado());

        return dto;
    }

    //Obtener todos los videojuegos
    public List<VideojuegoResponseDTO> obtenerTodos(){
        return videojuegoRepository.findAll()
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Obtener videojuego por ID
    public Optional<VideojuegoResponseDTO> obtenerPorId(Long id){
        return videojuegoRepository.findById(id)
        .map(this::mapToDTO);
    }

    //Guardar videojuego
    public VideojuegoResponseDTO guardar(VideojuegoRequestDTO dto){

        Consola consola = consolaRepository
            .findById(dto.getConsolaId())
            .orElseThrow();

        Genero genero = generoRepository
            .findById(dto.getGeneroId())
            .orElseThrow();

        Modalidad modalidad = modalidadRepository
            .findById(dto.getModalidadId())
            .orElseThrow();

        Videojuego videojuego = new Videojuego();

        videojuego.setNombreVideojuego(dto.getNombreVideojuego());
        videojuego.setEan(dto.getEan());
        videojuego.setConsola(consola);
        videojuego.setGenero(genero);
        videojuego.setModalidad(modalidad);
        videojuego.setPrecioVideojuego(dto.getPrecioVideojuego());
        videojuego.setEstado(dto.getEstado());

        Videojuego guardado = videojuegoRepository.save(videojuego);

        System.out.println("ID GUARDADO: " + guardado.getId());
        
        return mapToDTO(guardado);
    }

    //Eliminar videojuego
    public void eliminar(Long id){
        videojuegoRepository.deleteById(id);
    }

    //Buscar por nombre de videojuego
    public List <VideojuegoResponseDTO> buscarPorNombre(String texto){
        return videojuegoRepository.buscarPorNombre(texto)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por género de videojuego
    public List <VideojuegoResponseDTO> buscarPorGenero(String texto){
        return videojuegoRepository.buscarPorGenero(texto)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por consola
    public List <VideojuegoResponseDTO> buscarPorConsola(String texto){
        return videojuegoRepository.buscarPorConsola(texto)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por precio
    public List <VideojuegoResponseDTO> buscarPorPrecio(int precio){
        return videojuegoRepository.buscarPorPrecio(precio)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por modalidad
    public List <VideojuegoResponseDTO> buscarPorModalidad(String texto){
        return videojuegoRepository.buscarPorModalidad(texto)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por estado
    public List <VideojuegoResponseDTO> buscarPorEstado(String texto){
        return videojuegoRepository.buscarPorEstado(texto)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por EAN
    public List <VideojuegoResponseDTO> buscarPorEAN(String texto){
        return videojuegoRepository.buscarPorEan(texto)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por precio menor o igual
    public List <VideojuegoResponseDTO> buscarPorPrecioMenor(int precio){
        return videojuegoRepository.buscarPorPrecioMenor(precio)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    //Buscar por precio mayor o igual
    public List <VideojuegoResponseDTO> buscarPorPrecioMayor(int precio){
        return videojuegoRepository.buscarPorPrecioMayor(precio)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

}
