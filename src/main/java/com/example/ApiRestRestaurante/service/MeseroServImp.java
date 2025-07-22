package com.example.ApiRestRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ApiRestRestaurante.entity.Mesero;
import com.example.ApiRestRestaurante.repository.MeseroRepository;

//Para indicar que se ve a realiar un servicio se le pone la annotaión@Service
@Service
public class MeseroServImp {
	
	//Inyeccion de dependencia
	//Se realia con el esterotipo @Autowired
	//@Auotwired: permite tener un mejor control de los objetos que se inicializan
	//es la interface y el nombre del objeto
	
	@Autowired
	MeseroRepository meseroRepository;
	
//	----REALIZAR EL CRUD MESERO
//    ---LISTAR
//    ---GUARDAR(VALIDAR QUE EL ID Y NOMBRECOMPLETO NO SE REPITA)
//    ---BUSCARXID
//    ---EDITAR(VALIDAR QUE EL ID EXISTA, PARA PODER EDITAR)
//    ---ELIMINAR(VALIDAR QUE EL ID EXISTA, PARA PODER ELIMINAR)
	
//  ---LISTAR
	@Transactional(readOnly = true)
	public List<Mesero> mostar(){
		return meseroRepository.findAll();
	}
	
//  ---GUARDAR(VALIDAR QUE EL ID Y NOMBRECOMPLETO NO SE REPITA)
//	@Transactional
	public String guardar(Mesero mesero) {
		
		boolean bandera = false; 
		
		for(Mesero meseroEncontrado: meseroRepository.findAll()) {
			
			if(meseroEncontrado.getIdMesero().equals(mesero.getIdMesero())) {
				bandera =true;
				return "idMeseroYaExiste";
			}
			else if(meseroEncontrado.getNombre().equals(mesero.getNombre())) {
				bandera = true;
				return "nombreYaExiste";
			}
			else if(meseroEncontrado.getApellidoPaterno().equals(mesero.getApellidoPaterno())) {
				bandera =true;
				return "apellidoPaternoYaExiste";
			}
			else if(meseroEncontrado.getApellidoMaterno() == null)
				break;
			else if(meseroEncontrado.getApellidoMaterno().equals(mesero.getApellidoMaterno())) {
				bandera =true;
				return "apellidoMaternoYaExiste";
			}
		}
		if(!bandera) {
			meseroRepository.save(mesero);
		}
		
		
		
		return"guardado";
	}
	
//  ---BUSCARXID
	@Transactional(readOnly = true)
	public Mesero buscarXid(Integer idMesero) {
		return meseroRepository.findById(idMesero).orElse(null);
	}
	
	
//  ---EDITAR(VALIDAR QUE EL ID EXISTA, PARA PODER EDITAR)
	@Transactional
	public boolean  editar (Mesero mesero) {
		for(Mesero meseroEditar: meseroRepository.findAll()) {
			if(meseroEditar.getIdMesero().equals(mesero.getIdMesero())) {
				meseroRepository.save(mesero);
				return true;
			}
		}
		return false;
	}
	
//  ---ELIMINAR(VALIDAR QUE EL ID EXISTA, PARA PODER ELIMINAR)
	@Transactional
	public boolean eliminar(Integer idMesero) {
		Mesero meseroEncon = meseroRepository.findById(idMesero).orElse(null);
		
		if(meseroEncon != null) {
			meseroRepository.delete(meseroEncon);
			return true;
		}
		else 
			return false;
		
	}
	
	
}
