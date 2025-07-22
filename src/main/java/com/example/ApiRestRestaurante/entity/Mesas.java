package com.example.ApiRestRestaurante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/* CREATE TABLE MESAS(
ID NUMBER PRIMARY KEY,
NUM_MESA NUMBER NOT NULL,
NUM_SILLAS NUMBER NOT NULL,
ID_MESERO NUMBER NOT NULL,
FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
);*/

@Entity
@Table(name="MESAS")
@Data
public class Mesas {
	
	@Id
	@Column(name ="ID_MESA")
	private Integer idMesa;
	
	@Column(name="NUM_MESA")
	private Integer numMesa;
	
	@Column(name="NUM_SILLAS")
	private Integer numSillas;
	
	//Relacion entre las entidades
	//Muchas mesas pueden ser atendidas por un mesero
	
	//FetchType.EAGER: Cuando se consulta la entidad principal, JPA carga
	//Indemdiatamente tambien todas las entidades relacionadas
	//Procesos hilos 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_MESERO")
	private Mesero mesero;//Esta variable de tipo objeto debe ser igual 
	//a la que se puse en el mappedBy ="mesero"
	//Esta variable si va en todo el codigo de abajo

}
