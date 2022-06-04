package com.disney.rest;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.disney.dto.PeliculaDto;
import com.disney.dto.PeliculaEditarDto;
import com.disney.dto.PersonajeDto;
import com.disney.dto.PersonajeEditarDto;
import com.disney.entity.Genero;
import com.disney.entity.Pelicula;
import com.disney.entity.Personaje;
import com.disney.repository.GeneroRepository;
import com.disney.repository.PeliculasRepository;
import com.disney.repository.PersonajeRepository;

@RestController
@RequestMapping("/api")
public class ApiRest {

	@Autowired
	private PersonajeRepository personajeRepository;
	@Autowired
	private PeliculasRepository peliculasRepository;
	@Autowired
	private GeneroRepository generoRepository;

	@GetMapping("characters") // Filtro de personajes
	public List<PersonajeDto> getCharacters(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "age", required = false) Integer age,
			@RequestParam(value = "idMovie", required = false) String idMovie) {
		List<PersonajeDto> personajesDto = new ArrayList<>();
		List<Personaje> personajes = new ArrayList<>();

		if (name == null && age == null && idMovie != null) {
			personajes = personajeRepository.findAll();
			for (Personaje personaje : personajes) {
				for (Pelicula pelicula : personaje.getPeliculasAsociadas()) {
					if (pelicula.getTitulo().equals(idMovie)) {
						PersonajeDto personajeDto = new PersonajeDto();
						personajeDto.setImagen(personaje.getImagen());
						personajeDto.setNombre(personaje.getNombre());
						personajesDto.add(personajeDto);
					}
				}
			}
		} else if (name != null || age != null) {
			personajes = personajeRepository.findBynombre(name, age);
			for (Personaje personaje : personajes) {
				PersonajeDto personajeDto = new PersonajeDto();
				personajeDto.setImagen(personaje.getImagen());
				personajeDto.setNombre(personaje.getNombre());
				personajesDto.add(personajeDto);
			}

		} else {
			personajes = personajeRepository.findAll();
			for (Personaje personaje : personajes) {
				PersonajeDto personajeDto = new PersonajeDto();
				personajeDto.setImagen(personaje.getImagen());
				personajeDto.setNombre(personaje.getNombre());
				personajesDto.add(personajeDto);
			}
		}
		//test
		return personajesDto;
	}

	@GetMapping("movies") // Filtro de peliculas
	public List<PeliculaDto> getMovies(@RequestParam(value = "name", required = false) String titulo,
			@RequestParam(value = "genre", required = false) String genero) {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		if (titulo != null) {
			peliculas = peliculasRepository.findByTitulo(titulo);
		} else if (genero != null) {
			peliculas = peliculasRepository.findByGenero(genero);
			peliculas.forEach(p -> p.getGenerosAsociados()
					.forEach(g -> System.out.println("genero " + g.getNombre() + "pelicula: " + p.getTitulo())));
		} else {
			peliculas = peliculasRepository.findAll();
		}

		List<PeliculaDto> peliculasDto = new ArrayList<>();

		for (Pelicula pelicula : peliculas) {
			PeliculaDto peliculaDto = new PeliculaDto();
			peliculaDto.setTitulo(pelicula.getTitulo());
			peliculaDto.setImagen(pelicula.getImagen());
			peliculaDto.setFechaCreacion(pelicula.getFechaCreacion());
			peliculasDto.add(peliculaDto);
		}

		return peliculasDto;
	}

	// Creacion, Eliminacion y Edicion de Personajes
	@PostMapping("crearPersonaje")
	public String crearPersonaje(@RequestBody Personaje personaje) {
		peliculasRepository.saveAll(personaje.getPeliculasAsociadas());
		personajeRepository.save(personaje);
		return "Personaje creado";
	}

	@DeleteMapping("eliminarPersonaje/{idPersonaje}")
	public String eliminarPersonaje(@PathVariable Integer idPersonaje) {
		personajeRepository.deleteById(idPersonaje);
		return "Personaje eliminado";
	} 

	@PutMapping("editarPersonaje/characters/{idPersonaje}")
	public ResponseEntity<PersonajeEditarDto> editarPersonaje(@PathVariable Integer idPersonaje,
			@RequestBody Personaje personaje) throws AttributeNotFoundException {
		Personaje personajeEditar = personajeRepository.findById(idPersonaje).orElseThrow(
				() -> new AttributeNotFoundException("No se ha encontrado un personaje con la ID :: " + idPersonaje));
		personajeEditar.setEdad(personaje.getEdad());
		personajeEditar.setHistoria(personaje.getHistoria());
		personajeEditar.setImagen(personaje.getImagen());
		personajeEditar.setNombre(personaje.getNombre());
		personajeEditar.setPeso(personaje.getPeso());
		personajeRepository.save(personajeEditar);
		PersonajeEditarDto personajeDto = new PersonajeEditarDto(personajeEditar); 
		ResponseEntity<PersonajeEditarDto> entity = new ResponseEntity<PersonajeEditarDto>(personajeDto, HttpStatus.OK);
		return entity;
	}

	// Creacion, Eliminacion y Edicion de peliculas
	@PostMapping("crearPelicula")
	public String crearPelicula(@RequestBody Pelicula pelicula) {
		personajeRepository.saveAll(pelicula.getPersonajesAsociadas());
		generoRepository.saveAll(pelicula.getGenerosAsociados());
		peliculasRepository.save(pelicula);
		return "Pelicula creada";
	}

	@DeleteMapping("eliminarPelicula/{idPelicula}")
	public String eliminarPelicula(@PathVariable Integer idPelicula) {
		peliculasRepository.deleteById(idPelicula);
		return "Pelicula eliminado";
	}

	@PutMapping("editarPelicula/{idMovie}")
	public ResponseEntity<PeliculaEditarDto> editarPelicula(@PathVariable Integer idMovie, @RequestBody Pelicula pelicula)
			throws AttributeNotFoundException {
		Pelicula peliculaEditar = peliculasRepository.findById(idMovie).orElseThrow(
				() -> new AttributeNotFoundException("No se ha encontrado una pelicula con la ID :: " + idMovie));
		peliculaEditar.setImagen(pelicula.getImagen());
		peliculaEditar.setTitulo(pelicula.getTitulo());
		peliculaEditar.setFechaCreacion(pelicula.getFechaCreacion());
		peliculaEditar.setCalificacion(pelicula.getCalificacion());
		peliculasRepository.save(peliculaEditar);
		PeliculaEditarDto peliculaDto = new PeliculaEditarDto(peliculaEditar); 
		ResponseEntity<PeliculaEditarDto> entity = new ResponseEntity<PeliculaEditarDto>(peliculaDto, HttpStatus.OK);
		return entity;
	}

	// Creacion de Generos
	@PostMapping("crearGenero")
	public String crearGenero(@RequestBody Genero genero) {
		peliculasRepository.saveAll(genero.getPeliculasAsociadas());
		generoRepository.save(genero);
		return "Genero creado";
	}

	// Agregar/Remover personajes a una pelicula

	@PutMapping("anadirPersonaje/{idMovie}")
	public ResponseEntity<Pelicula> anadirPersonaje(@PathVariable Integer idMovie,
			@RequestBody Pelicula pelicula) throws AttributeNotFoundException {
		Pelicula peliculaDto = peliculasRepository.findById(idMovie).orElseThrow(
				() -> new AttributeNotFoundException("No se ha encontrado una pelicula con la ID :: " + idMovie));
		peliculaDto.setPersonajesAsociadas(pelicula.getPersonajesAsociadas());
		final Pelicula actualizarPersonajes = peliculasRepository.save(pelicula);
		return ResponseEntity.ok(actualizarPersonajes);
	}

	@DeleteMapping("removerPersonaje/movies/{idMovie}/characters/{idPersonaje}")
	public ResponseEntity<Personaje> removerPersonaje(@PathVariable Integer idMovie,
			@PathVariable Integer idPersonaje, @RequestBody Personaje personaje) throws AttributeNotFoundException {
		peliculasRepository.findById(idMovie);
		Personaje personajeDto = personajeRepository.findById(idPersonaje).orElseThrow(
				() -> new AttributeNotFoundException("No se ha encontrado un personaje con la ID :: " + idPersonaje));
		personajeDto.setPeliculasAsociadas(personaje.getPeliculasAsociadas());
		final Personaje removerPersonajes = personajeRepository.save(personaje);
		return ResponseEntity.ok(removerPersonajes);
	}
}
