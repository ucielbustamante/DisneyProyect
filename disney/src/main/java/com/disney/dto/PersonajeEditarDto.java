package com.disney.dto;

import java.util.ArrayList;
import java.util.List;

import com.disney.entity.Pelicula;
import com.disney.entity.Personaje;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class PersonajeEditarDto {
	
	private Integer id;
	private String imagen;
	private String nombre;
	private Integer edad;
	private String peso;
	private String historia;
	private List<PeliculaEditarDto> peliculasAsociadas;

	public PersonajeEditarDto() {
		// TODO Auto-generated constructor stub
	}
	public PersonajeEditarDto(Personaje personaje) {
		this.edad = personaje.getEdad();
		this.historia = personaje.getHistoria();
		this.id = personaje.getId();
		this.imagen = personaje.getImagen();
		this.nombre = personaje.getNombre();
		this.peso = personaje.getPeso();
		peliculasAsociadas = new ArrayList<PeliculaEditarDto>();
		for (Pelicula pelicula : personaje.getPeliculasAsociadas()) {
			PeliculaEditarDto dto = new PeliculaEditarDto(pelicula);
			peliculasAsociadas.add(dto);
		}
	}


	public List<PeliculaEditarDto> getPeliculasAsociadas() {
		return peliculasAsociadas;
	}


	public void setPeliculasAsociadas(List<PeliculaEditarDto> peliculasAsociadas) {
		this.peliculasAsociadas = peliculasAsociadas;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public String getHistoria() {
		return historia;
	}


	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
	
	
	
}
