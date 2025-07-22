package com.example.ApiRestRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiRestRestaurante.entity.Mesero;

public interface MeseroRepository extends JpaRepository<Mesero, Integer> {
	//Entidad(Se ponde el nombre de la clase que representa la entidad
	//@Entity es en donde se encuentra definida la tabla
	//(TipodeId: es el tipo de dato del campo de la lllave primaria
	//En la entidad @Entity

}
