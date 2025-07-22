package com.example.ApiRestRestaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestRestaurante.entity.Mesas;
import com.example.ApiRestRestaurante.service.MesasServImp;

@RestController
@RequestMapping(path ="mesas/api")
@CrossOrigin
public class MesasWS {
	
	@Autowired
	MesasServImp mesasServImp;
	
	//http://localhost:9000/mesas/api/mostrar
	@GetMapping(path="mostrar")
	public List<Mesas> mostrar(){
		return mesasServImp.mostrar();
	}
	
	//http://localhost:9000/mesas/api/guardar
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesa){
		try {
			String response = mesasServImp.guardar(mesa);
			
			if(response.equals("idMesaYaExiste"))
				return new ResponseEntity<String>("Ese idMesa ya existe, no se puede guardar",HttpStatus.OK);
			else if(response.equals("numeroMesaYaExiste"))
				return new ResponseEntity<String>("Ese numero de mesa ya existe, no se puede guardar",HttpStatus.OK);
			else if(response.equals("idMeseroNoExiste"))
				return new ResponseEntity<String>("Ese idMesero no existe, no se puede guardar",HttpStatus.OK);
			else 
				return new ResponseEntity<Mesas>(mesa,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al guardar" + e.getMessage(),HttpStatus.OK);
		}
		
	}


	//http://localhost:9000/mesas/api/buscarXid
	@PostMapping(path="buscarXid")
	public ResponseEntity<?>buscarXid(@RequestBody Mesas mesa){
		try {
			Mesas mesas = mesasServImp.buscarXid(mesa.getIdMesa());
			if(mesas != null)
				return new ResponseEntity<Mesas>(mesas,HttpStatus.OK);
			else
				return new ResponseEntity<String>("Ese idMesa no existe, error al buscar",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al buscar " +e.getMessage() ,HttpStatus.OK);

		}
		
	}
	//http://localhost:9000/mesas/api/editar
	@PutMapping(path ="editar")
	public ResponseEntity<?>editar(@RequestBody Mesas mesa){
		try {
			String mesaEncontrada = mesasServImp.editar(mesa);
			if(mesaEncontrada.equals("idMeseroNoExiste"))
				return new ResponseEntity<String>("Ese idMesero no existe, error al guardar",HttpStatus.OK);
			else if(mesaEncontrada.equals("idMesaNoEiste"))
				return new ResponseEntity<String>("Ese idMesa no existe, error al guardar",HttpStatus.OK);
			else
				return new ResponseEntity<Mesas>(mesa,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al editar " + e.getMessage(),HttpStatus.OK);

		}
		
	}
	//http://localhost:9000/mesas/api/eliminar/3
	@DeleteMapping(path ="eliminar/{idMesa}")
	public ResponseEntity<String>eliminar(@PathVariable("idMesa")Integer idMesa){
		try {
			boolean mesaFind = mesasServImp.eliminar(idMesa);
			if(mesaFind ==false)
				return new ResponseEntity<String>("Ese idMesa no existe, error al eliminar",HttpStatus.OK);
			else 
				return new ResponseEntity<String>("Se elimino con exito",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al eliminar" + e.getMessage(),HttpStatus.OK);

		}
		
	}

}
