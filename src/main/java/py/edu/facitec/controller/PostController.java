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

import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;

//aplicar la arquitectura rest
//Representational State Transfer - Solicitudes sin almacenamiento de estado
@RestController
@RequestMapping("/api")
//Afecta a todos los metodos de la clase
public class PostController {
	// Autowired de suscrito repository
	@Autowired
	private PostRepository  postRepository;

	// Api
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getposts() {
		List<Post> posts = new ArrayList<>();
		// Buscar todos
		posts = postRepository.findAll();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	//Post se utiliza para crear un elemento
	@PostMapping("/post")
	public ResponseEntity<Post> guardarpost(@RequestBody Post post) {

		postRepository.save(post);

		// devolver el objeto nuevo creado //id nuevo
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	//Consulta api/suscrito/{ID}
	
	@GetMapping("/posts/{texto}")
	public ResponseEntity<Post>
	                //Recibir por parametro el valor
	getOnepost(@PathVariable Long codigo){
		
		Optional<Post> post = postRepository.findById(codigo);

		    //Verifica si se encontro
		if (post.isPresent()) {
			return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	}
	//api/suscrito/ID
	//Eliminar suscrito por id o codigo. Delete
	@DeleteMapping("/posts/{codigo}")
	public ResponseEntity<Post>
	                //Recibir por parametro el valor
	eliminarOneSuscrito(@PathVariable Long codigo){
		
		Optional<Post> post = postRepository.findById(codigo);

		    //Verifica si se encontro
		if (post.isPresent()) {
			postRepository.deleteById(codigo);
		
			return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	}
	}


