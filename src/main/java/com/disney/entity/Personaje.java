package com.disney.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONAJE")
public class Personaje implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3156930714343221979L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "IMAGEN")
	private String imagen;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "EDAD")
	private Integer edad;

	@Column(name = "PESO")
	private String peso;

	@Column(name = "HISTORIA")
	private String historia;
	
	
	@ManyToMany
	@JoinTable(
			name = "personaje_pelicula",
			joinColumns = @JoinColumn(name = "id_personaje"),
			inverseJoinColumns = @JoinColumn(name = "id_pelicula"))
	private Set<Pelicula> peliculasAsociadas;

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

	public Set<Pelicula> getPeliculasAsociadas() {
		return peliculasAsociadas;
	}

	public void setPeliculasAsociadas(Set<Pelicula> peliculasAsociadas) {
		this.peliculasAsociadas = peliculasAsociadas;
	}
	

}
