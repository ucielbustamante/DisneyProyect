package com.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.disney.entity.Pelicula;

public interface PeliculasRepository extends CrudRepository<Pelicula, Integer>  {
    @Override
    List<Pelicula> findAll();
    
    @Query("select p from Pelicula p where p.titulo = :titulo")
    List<Pelicula> findByTitulo(String titulo);

    
    @Query("select p from Pelicula p inner join p.generosAsociados generosAsociado where generosAsociado.nombre in :genero")
    List<Pelicula> findByGenero(String genero);

}
	

