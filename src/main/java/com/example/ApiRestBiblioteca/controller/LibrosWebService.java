package com.example.ApiRestBiblioteca.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestBiblioteca.model.Libros;
import com.example.ApiRestBiblioteca.service.LibrosServImp;

@RestController
@RequestMapping(path ="LibrosWebService")
@CrossOrigin
public class LibrosWebService {

	//@RestController: Srive para marcar como contorlador de solicitudes y se 
	//utiliza para crear servicios RestFull: web service de tipo rest
	
	//@RequestMapping: Sirve para formar nuestros recursos o las Url
	
	//@CrossOrigin: Es por seguridad para que nuestra aplicacion no sea blouqueado por el navegador
	
	//Inyeccion de dependencia
	@Autowired
	LibrosServImp librosServImp;
	
	//Peticiones: get, post, put...
	//Probar la peticion se necesita formar la Url, porque se esta ocupando el protocolo http
	//Como formar la Url: urlServidorLocal/pathClase/pathMetodo
	
	//http://localhost:9000/LibrosWebService/mostrar
	
	@GetMapping(path = "mostrar")
	public List<Libros> mostrar(){
		return librosServImp.mostrar();
		
		//Esta abierto significa que puede retornar el tipo de dato que sea 
		//httpstatus.ok es para enviar msjs 
	
	}
	
	//http://localhost:9000/LibrosWebService/guardar
	@PostMapping(path ="guardar")
	public ResponseEntity<?>guardar(@RequestBody Libros libro){
		
		String response = librosServImp.guardar(libro);
		
		if(response.equals("isbnYaExiste"))
			return new ResponseEntity<String>("Ese ISBN ya existe, no se puede guardar el registro",HttpStatus.OK);
		else if(response.equals("tituloYaExiste"))
			return new ResponseEntity<String>("Ese titulo ya existe, no se puede guardar el registro",HttpStatus.OK);
		else
			return new ResponseEntity<Libros>(libro, HttpStatus.OK);
		
	}
	
	//http://localhost:9000/LibrosWebService/buscar
	@PostMapping (path = "buscar")//Este se activa cuando se hace una solicitud POST, con la URL indicada
	
	//@RequestBody convierte automaticamente el JSON en un objeto Java Libros
	//Ejemplo  {"idLibro": 1}, eso es lo que se pone en postman
	//Y con el @RequestBody lo covierte a idLibro =1
	public Libros buscarXid(@RequestBody Libros libro) {
		
		//obtiene el ID del libro que se mandó por JSON.
		//libro.getIdLibro() = 1
		//Entonces ese metodo esta declarado en LibrosServImp
		//librosSerImp.buscarXid(1)
		//Ese archivo es el servicio y ahora se le pasa el control al servicio...
		return librosServImp.buscarXid(libro.getIdLibro());
	}
	
	//http://localhost:9000/LibrosWebService/editar
	@PutMapping(path ="editar")
	
	//El valor que se recibe aqui es el que ingresa el usuario (los nuevos registros)
	public ResponseEntity<?>editar(@RequestBody Libros libro){
		
		//Se manda a llamar el metodo el cual esta en la parte de servicio
		//En donde ya se hizo la logica y se comprueba si existe el registro o no
		boolean response = librosServImp.editar(libro);
		
		if (response == false)//En la parte de servicio se devuelve "true"  o "false" por eso se compara aqui asi
			return new ResponseEntity<String>("Ese registro no existe, no se puede editar",HttpStatus.OK);
		else
			return new ResponseEntity<Libros>(libro, HttpStatus.CREATED);//Se devuelve el registro actualizado 	
	}
	
	//http://localhost:9000/LibrosWebService/eliminar
	@PostMapping(path ="eliminar")
	//@RequestBody por ejemplo se ingresa el id a eliminar, es un JSON lo convierte a un objeto
	public ResponseEntity<String>eliminar(@RequestBody Libros libro){
		
		//Primero se toma el valor que ha ingresado el usuario por ejemplo idLibro=1
		boolean response = librosServImp.eliminar(libro.getIdLibro());
		
		if (response == true)//Si es igual a true que es lo que devuelve se hace esta linea
			return new ResponseEntity<String>("Registro elimnado con exito",HttpStatus.OK);
		else
			return new ResponseEntity<String>("No existe ese registro",HttpStatus.OK);
	}
	//http://localhost:9000/LibrosWebService/buscarXisbn/{isbn}
	//http://localhost:9000/LibrosWebService/buscarXisbn/34284342
	@GetMapping(path = "buscarXisbn/{isbn}")//Se busca por medio de la URL 
	public Libros buscarXisbn(@PathVariable("isbn") Long isbn) {
		return librosServImp.buscarXisbn(isbn);//Entra a este metodo con el parametro que ingreso el usuario
	}
	
	//http://localhost:9000/LibrosWebService/buscarXtitulo/{titulo}
	//http://localhost:9000/LibrosWebService/buscarXtitulo/SQL
	@GetMapping(path = "buscarXtitulo/{titulo}")
	public ResponseEntity<?> buscarXtitulo(@PathVariable("titulo")String titulo){
		try {
			return new ResponseEntity<Libros>(librosServImp.buscarXtitulo(titulo),HttpStatus.CREATED);//Entra a este metodo con el parametro que ingreso el usuario
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al buscar ese registro" + e.getMessage(),HttpStatus.OK);
		}
	}
}


