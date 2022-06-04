package com.disney.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.disney.entity.Pelicula;
import com.disney.entity.Personaje;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class PeliculaEditarDto {
	private Integer id;
	private String imagen;
	private String titulo;
	private Date fechaCreacion;
	private Integer calificacion;
	private List<PersonajeEditarDto> personajesAsociadas;
	
	public PeliculaEditarDto(Pelicula pelicula) {
		this.id = pelicula.getId();
		this.calificacion = pelicula.getCalificacion();
		this.fechaCreacion = pelicula.getFechaCreacion();
		this.imagen = pelicula.getImagen();
		this.titulo = pelicula.getTitulo();
		personajesAsociadas = new ArrayList<PersonajeEditarDto>();
		for (Personaje personaje : pelicula.getPersonajesAsociadas()) {
			PersonajeEditarDto dto = new PersonajeEditarDto();
			dto.setEdad(personaje.getEdad());
			dto.setHistoria(personaje.getHistoria());
			dto.setId(personaje.getId());
			dto.setImagen(personaje.getImagen());
			dto.setNombre(personaje.getNombre());
			dto.setPeso(personaje.getPeso());
			personajesAsociadas.add(dto);
		}
	}
	
	public List<PersonajeEditarDto> getPersonajesAsociadas() {
		return personajesAsociadas;
	}

	public void setPersonajesAsociadas(List<PersonajeEditarDto> personajesAsociadas) {
		this.personajesAsociadas = personajesAsociadas;
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	
}
