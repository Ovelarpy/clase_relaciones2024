package py.edu.facitec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;

//aplicar la arquitectura rest
//Representational State Transfer - Solicitudes sin almacenamiento de estado
@RestController
@RequestMapping("/api")
//Afecta a todos los metodos de la clase
public class ComentarioController {
	// Autowired de suscrito repository
	@Autowired
	private ComentarioRepository  comentarioRepository;

	// Api
	@GetMapping("/comentarios")
	public ResponseEntity<List<Comentario>> getComentarios() {
		List<Comentario> comentarios = new ArrayList<>();
		// Buscar todos
		comentarios = comentarioRepository.findAll();
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
	}

	//Post se utiliza para crear un elemento
	@PostMapping("/comentario")
	public ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario) {

		comentarioRepository.save(comentario);

		// devolver el objeto nuevo creado //id nuevo
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
	}
	//Consulta api/suscrito/{ID}
	
	@GetMapping("/comentario/{codigo}")
	public ResponseEntity<Comentario>
	                //Recibir por parametro el valor
	getOneComentario(@PathVariable Long codigo){
		
		Optional<Comentario> comentario = comentarioRepository.findById(codigo);

		    //Verifica si se encontro
		if (comentario.isPresent()) {
			return new ResponseEntity<Comentario>(comentario.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
	}
	//api/suscrito/ID
	//Eliminar suscrito por id o codigo. Delete
	@DeleteMapping("/comentarios/{codigo}")
	public ResponseEntity<Comentario>
	                //Recibir por parametro el valor
	eliminarOneSuscrito(@PathVariable Long codigo){
		
		Optional<Comentario> comentario = comentarioRepository.findById(codigo);

		    //Verifica si se encontro
		if (comentario.isPresent()) {
			comentarioRepository.deleteById(codigo);
		
			return new ResponseEntity<Comentario>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
	}
	}


