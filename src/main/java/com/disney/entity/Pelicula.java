package com.disney.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PELICULA")
public class Pelicula implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4302557396662403099L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "ID")
	private Integer id;
	@Column(name = "IMAGEN")
	private String imagen;
	@Column(name = "TITULO")
	private String titulo;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "CALIFICACION")
	private Integer calificacion;

	//@ManyToMany(fetch = FetchType.LAZY) // Relacion personaje-pelicula
	@ManyToMany
	@JoinTable(name = "personaje_pelicula", joinColumns = @JoinColumn(name = "id_pelicula"), inverseJoinColumns = @JoinColumn(name = "id_personaje"))
	private Set<Personaje> personajesAsociadas;

	@ManyToMany // Relacion pelicula-genero
	@JoinTable(name = "genero_pelicula", joinColumns = @JoinColumn(name = "id_pelicula"), inverseJoinColumns = @JoinColumn(name = "id_genero"))
	private Set<Genero> generosAsociados;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Personaje> getPersonajesAsociadas() {
		return personajesAsociadas;
	}

	public void setPersonajesAsociadas(Set<Personaje> personajesAsociadas) {
		this.personajesAsociadas = personajesAsociadas;
	}

	public Set<Genero> getGenerosAsociados() {
		return generosAsociados;
	}

	public void setGenerosAsociados(Set<Genero> generosAsociados) {
		this.generosAsociados = generosAsociados;
	}

}
