package com.example.ApiRestRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ApiRestRestaurante.entity.Mesas;
import com.example.ApiRestRestaurante.entity.Mesero;
import com.example.ApiRestRestaurante.repository.MesasRepository;
import com.example.ApiRestRestaurante.repository.MeseroRepository;

@Service
public class MesasServImp {
	
	//Inyeccion de dependencia 
	//Esta se realiza con el esterotipo @Autowired
	
	//@Autowired:Permite tener un mejor control de los objetos que se inicializan 
	//Es la interface y el nombre del objeto
	
	@Autowired
	MesasRepository mesaRepository;
	
	@Autowired
	MeseroRepository meseroRepository;
	
//	----REALIZAR EL CRUD MESAS
//    ---LISTAR
//    ---GUARDAR(VALIDAR QUE EL ID_MESA Y NUM_MESA NO SE REPITA, ID_MESERO EXISTA)
//    ---BUSCARXID
//    ---EDITAR(VALIDAR QUE EL ID_MESA EXISTA Y ID_MESERO EXISTA, PARA PODER EDITAR)
//    ---ELIMINAR(VALIDAR QUE EL ID_MESA EXISTA, PARA PODER ELIMINAR)
	
	//Listar
	@Transactional(readOnly = true)
	public List<Mesas> mostrar(){
		return (List<Mesas>) mesaRepository.findAll();
	}
	
//  ---GUARDAR(VALIDAR QUE EL ID_MESA Y NUM_MESA NO SE REPITA, ID_MESERO EXISTA)
	@Transactional
	public String guardar(Mesas mesa) {
		boolean banderaMesero =false;
		boolean banderaMesa = false;
		
		//Se empieza siempre validando las llaves foraneas
		for(Mesero mesero : meseroRepository.findAll()) {
			if(mesero.getIdMesero().equals(mesa.getMesero().getIdMesero())) {
				//Si existe el IdMesero
				banderaMesero = true;
			
			//En la tabla mesas vienen todos los campos y de alli se toma el campo de idMesero
			for(Mesas mesaBuscada: mesaRepository.findAll()) {
				if(mesaBuscada.getIdMesa().equals(mesa.getIdMesa())){
					banderaMesa = true; 
					return "idMesaYaExiste";
				}
				else if(mesaBuscada.getNumMesa().equals(mesa.getNumMesa())) {
					banderaMesa = true; 
					return "numeroMesaYaExiste";
				}
			}
			break;
			}
		}
		if(!banderaMesero) {
			//No existe idmesero
			banderaMesa = true;
			return "idMeseroNoExiste";
		}
		else if(!banderaMesa)
			mesaRepository.save(mesa);
		
		return "guardado";
		
	}
	
//  ---BUSCARXID
	@Transactional(readOnly = true)
	public Mesas buscarXid(Integer idMesas) {
		return mesaRepository.findById(idMesas).orElse(null);
		
	}
	
//  ---EDITAR(VALIDAR QUE EL ID_MESA EXISTA Y ID_MESERO EXISTA, PARA PODER EDITAR)
	@Transactional
	public String editar(Mesas mesa) {
		
		//Primero se validan las llaves foraneas
		Mesero meseroEncontrado = meseroRepository.findById(mesa.getMesero().getIdMesero()).orElse(null);
		Mesas mesaEncontrada = buscarXid(mesa.getIdMesa());
		
		if(meseroEncontrado!=null) {
			if(mesaEncontrada!=null) {
				mesaRepository.save(mesa);
				return "editado";
			}
			else 
				return "idMesaNoEiste";
		}
		else 
			return "idMeseroNoExiste";
			
	}

//  ---ELIMINAR(VALIDAR QUE EL ID_MESA EXISTA, PARA PODER ELIMINAR)
	@Transactional
	public boolean eliminar(Integer idMesas) {
		Mesas mesaEncont = mesaRepository.findById(idMesas).orElse(null);
		if(mesaEncont==null)
			return false;
		else {
			mesaRepository.delete(mesaEncont);
			return true;
		}
		
		
	}

	
	

}

