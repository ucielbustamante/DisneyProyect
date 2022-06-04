package com.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.disney.entity.Genero;

public interface GeneroRepository extends CrudRepository<Genero, Integer>  {
    @Override
    List<Genero> findAll();
    
    @Query("select g from Genero g where g.nombre = :genero")
    List<Genero> findByGenero(String genero);
}
