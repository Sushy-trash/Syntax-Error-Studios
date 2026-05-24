package com.example.syntax_error_studios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.syntax_error_studios.model.Consola;
@Repository
public interface ConsolaRepository extends JpaRepository<Consola,Long> {

}
