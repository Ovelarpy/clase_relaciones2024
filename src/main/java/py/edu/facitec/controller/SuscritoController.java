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

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;

//aplicar la arquitectura rest
//Representational State Transfer - Solicitudes sin almacenamiento de estado
@RestController
@RequestMapping("/api")
//Afecta a todos los metodos de la clase
public class SuscritoController {
	// Autowired de suscrito repository
	@Autowired
	private SuscritoRepository suscritoRepository;

	// Api
	@GetMapping("/suscritos")
	public ResponseEntity<List<Suscrito>> getSuscritos() {
		List<Suscrito> suscritos = new ArrayList<>();
		// Buscar todos
		suscritos = suscritoRepository.findAll();
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}

	//Post se utiliza para crear un elemento
	@PostMapping("/suscrito")
	public ResponseEntity<Suscrito> guardarSuscrito(@RequestBody Suscrito suscrito) {

		suscritoRepository.save(suscrito);

		// devolver el objeto nuevo creado //id nuevo
		return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);
	}
	//Consulta api/suscrito/{ID}
	
	@GetMapping("/suscritos/{codigo}")
	public ResponseEntity<Suscrito>
	                //Recibir por parametro el valor
	getOneSuscrito(@PathVariable Long codigo){
		
		Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);

		    //Verifica si se encontro
		if (suscrito.isPresent()) {
			return new ResponseEntity<Suscrito>(suscrito.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Suscrito>(HttpStatus.NOT_FOUND);
		}
	}
	//api/suscrito/ID
	//Eliminar suscrito por id o codigo. Delete
	@DeleteMapping("/suscritos/{codigo}")
	public ResponseEntity<Suscrito>
	                //Recibir por parametro el valor
	eliminarOneSuscrito(@PathVariable Long codigo){
		
		Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);

		    //Verifica si se encontro
		if (suscrito.isPresent()) {
			suscritoRepository.deleteById(codigo);
		
			return new ResponseEntity<Suscrito>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Suscrito>(HttpStatus.NOT_FOUND);
		}
	}
	}


