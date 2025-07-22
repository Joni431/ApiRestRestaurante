package com.example.ApiRestRestaurante.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/*CREATE TABLE MESERO(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(70) NOT NULL,
APP VARCHAR2(70) NOT NULL,
APM VARCHAR2(70),
SUELDO NUMBER NOT NULL
);*/
@Entity
@Table(name ="MESERO")
@Data
public class Mesero {
	
	@Id
	@Column(name ="ID_MESERO")
	private Integer idMesero;
	
	@Column(name ="NOMBRE")
	private String nombre;
	
	@Column(name="APP")
	private String apellidoPaterno;
	
	@Column(name ="APM")
	private String apellidoMaterno;
	
	@Column(name ="SUELDO")
	private Integer sueldo;
	
	//Un mesero puede atender a muchas mesas
	@OneToMany(mappedBy ="mesero", cascade = CascadeType.ALL)
	@JsonIgnore//Es para omitir una porpiedad o lista de propiedades
	private List<Mesas> mylist = new ArrayList<>();
	//Esta lista no se agrega en todo el codigo de abajo
	//La lista se declara solo para relacionar
	
	
	
	

}
