package com.example.ApiRestBiblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ApiRestBiblioteca.dao.LibrosDao;
import com.example.ApiRestBiblioteca.model.Libros;

//Para indicarle  que vamos a relizar un servicio se pone el @Service

@Service
public class LibrosServImp {

	//Inyeccion de dependencia
	//Como se realiza la inyección de dependencia en spring boot con el @Autowired
	
	//@Autowired: Permite tener un mejor contorl de los objetos que se inicializan
	//Es la interface y el nombre del objeto 
	
	@Autowired
	LibrosDao librosDao;
	
	//Transactional revisa si todo esta bien y si si lo ejecuta y hace un commit automaticamante
	//Transacción de solo lectura
	@Transactional(readOnly = true)
	public List<Libros> mostrar(){
	
		List<Libros> newList = librosDao.findAll();
		
		return newList;
	}
	
	//Para desarrollar las funcionalidades que les pidan en spring boot:
		//1---Ocupando los metodos de los repositorios
		//2---Podemos desarrollar nosostros la logica: Ciclos, condiciones, banderas...
		//3---Podemos agregar metodos bajo el resorte de los repositorios ---- findBy
		//4---Podemos agregar querys nativas en los reporitorios
	
	//Validaciones: que es el isbn y el titulo que no se repitan 
	@Transactional
	public String guardar (Libros libro) {
		
		boolean bandera = false; 
		
		for (Libros books: librosDao.findAll()) {
			
			//Si se tiene los tipos de datos primitivos y estan parseados (long Long)
			//Se comparan con el .equals
			//Si se tiene los tipos de datos primitivos y no estan parseados se ocupa el ==
			
			if(books.getIsbn().equals(libro.getIsbn())) {
				bandera = true;
				return "isbnYaExiste";
			}
			else if(books.getTitulo().equals(libro.getTitulo())) {
				bandera = true;
				return "tituloYaExiste";
			}
			
		}
		
		if(!bandera) {
			librosDao.save(libro);
		}
		
		return "guradado";
	}
	//Aqui continua la parte de buscar
	@Transactional(readOnly = true)//Transacción de solo lectura
	//Recibe el parametro idLibro=1, que viene de la parte del controlador(LibrosWebService)
	public Libros buscarXid(Long idLibro) {
		
		//Entra al metodo y busca en la BD con el idLibro =1
		//findById(idLibro) hace la siguiente consulta
		//SELECT * FROM libros WHERE id_libro = 1;
		//Este devuele un Optional<Libros>, quiere decir que puede contener el registro o no
		//Si se enuentra el registro lo devuelve y si no devuelve null
		Libros libro = librosDao.findById(idLibro).orElse(null);
		
		//Devuleve lo que se encontro ya sea el regsitro completo o null
		//Esto se envia como respuesta HTTP 
		return libro;
	}
	
	//Validaciones: idExista
	@Transactional
	//Recibe como parametro el objeto de Libros, (Los nuevos registros, que ingreso el usuario) 
	//Este parametro viene de la parte del controllador
	//libro, este es el parametro 
	public boolean editar(Libros libro) {
		
		//Busca primero si existe ese IdLibro para poder hacer la modificación
		Libros libroEncontrado = librosDao.findById(libro.getIdLibro()).orElse(null);
		
		//Si si existe se va a guardar los nuevos registros
		if(libroEncontrado != null) {
			librosDao.save(libro);
			return true;
		}else 
			return false;
	}
	
	//Validaciones: IdExista
	//Recibe como el parametro el idLIbro para comprobar si existe o no ese registro
	//El parametro viene de la parte del contollador (el que ingreso el usuario)
	//libro.getIdLibro(), viene de aqui por ejemplo idLibro=1
	//Esta funcion devuelve un tipo de dato booleano
	public boolean eliminar(Long idLibro) {
		
		//Busca el idLIbro en la base datos
		Libros libroEncont = buscarXid(idLibro);
		
		if(libroEncont != null) {//Si si lo encuentra va a eliminar ese registro
			librosDao.delete(libroEncont);
			//librosDao.deleteById(idLibro);
			return true;
		}else
			return false;
	}

	//3---Podemos agregar metodos bajo el resorte de los repositorios ---- findBy
	@Transactional(readOnly = true)//Es de solo lectura
	
	//Recibe el parametro que viene de la parte del usuario
	public Libros buscarXisbn(Long isbn) {
		
		//devuelve el registro todo elencontrado 
		return librosDao.findByIsbn(isbn);
	}
	
	//podemos agregar querys nativas en los reporitorios
	@Transactional(readOnly = true)
	public Libros buscarXtitulo(String titulo) {
		//La logica de este metodo esta en el LibrosDao
		return librosDao.buscarXtitulo(titulo);//busca el titulo ingresado por el usuario es el parametro
	}
}
