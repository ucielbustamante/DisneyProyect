package com.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.disney.entity.Personaje;

public interface PersonajeRepository extends CrudRepository<Personaje, Integer>  {
    @Override
    List<Personaje> findAll();
    
    @Query("select p from Personaje p where p.nombre = :nombre or p.edad = :edad")
    List<Personaje> findBynombre(String nombre, Integer edad);

}
