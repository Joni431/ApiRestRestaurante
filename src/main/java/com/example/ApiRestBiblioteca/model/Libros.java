package com.example.ApiRestBiblioteca.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*CREATE TABLE LIBROS_15(
ID_LIBRO NUMBER PRIMARY KEY, 
ISBN NUMBER NOT NULL,
TITULO VARCHAR2(80) NOT NULL, 
NUM_PAG INT NOT NULL, 
FECHA_PUBLICACION DATE NOT NULL
);
*/

//Mapear la tabla 
//Estereotipos o anotaciones: ya se tiene acceso ha cierta dependencia
//De preferencia los tipos de datos primitivos se ponen parseados las variables primitivas para que pueda aceptar valores nulos 
//(long asi no,  Long asi si) cuando se hace el mapeo 

@Entity
@Table(name="LIBROS_15")
@NoArgsConstructor //Constructor vacio
@AllArgsConstructor//Constructor con todos los parametros
@Data //Este ya tiene el metodo ToString, Get y Set
public class Libros {

	//Los campos de la tabla 
	
	@Id //Todas las tablas tienen un identificador unico
	@GeneratedValue(strategy = GenerationType. IDENTITY)//Solo se pone si el id tiene el autoincrementable
	@Column(name = "ID_LIBRO", columnDefinition = "NUMBER", nullable = false)
	private Long idLibro;
	
	@Column(name = "ISBN", columnDefinition = "NUMBER", nullable = false)
	private Long isbn;
	
	@Column(name = "TITULO", columnDefinition = "VARCHAR2(80)", nullable = false)
	private String titulo;
	
	@Column(name = "NUM_PAG", columnDefinition = "INT", nullable = false)
	private Integer numPag;
	
	@Column(name = "FECHA_PUBLICACION", columnDefinition = "DATE", nullable = false)
	private Date fechaPublicacion;
	
	
	
	
}
 