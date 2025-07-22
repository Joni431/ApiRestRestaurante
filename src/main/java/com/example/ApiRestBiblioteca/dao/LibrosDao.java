package com.example.ApiRestBiblioteca.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ApiRestBiblioteca.model.Libros;

//Repositorios
//1. JpaRepository: tiene los metodos del crud y la paginacion
//2. CrudRepository: TIene los metodos del crud

//Vienen de la dependencia de Spring data JPA

//Extends con eso se hereda

public interface LibrosDao extends JpaRepository<Libros, Long>{
	//<Entidad(Se pone el nombre de la clase que representa la entidad @Entity es en donde se encuentra definida la tabla),
		//TipodeId (Es el tipo de dato del campo de la llave primaria en la etniedad @Entity>
	
	//Agregar un metodo bajo el resorte ---findBy --atributo(este atributo debe
	//ser igual al que esta en el mapeo) para buscar isbn
	
	public Libros findByIsbn(Long isbn);

	//esta es la query nativa
	@Query(value = "SELECT * FROM LIBROS_15 WHERE TITULO =:TITULO", nativeQuery = true)
	public Libros buscarXtitulo(@Param("TITULO")String titulo);
}
