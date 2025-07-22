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

import com.example.ApiRestRestaurante.entity.Mesero;
import com.example.ApiRestRestaurante.service.MeseroServImp;

@RestController
@RequestMapping(path="MeseroWebService")
@CrossOrigin
public class MeseroWS {
	
	//Inyeccion de dependencia 
	@Autowired
	MeseroServImp meseroservimp;
	//Es la annotacion que tiene el @Service
	
	//http://localhost:9000/MeseroWebService/show
	@GetMapping(path="show")
	public List<Mesero> mostrar(){
		return meseroservimp.mostar();
	}
	
	//http://localhost:9000/MeseroWebService/save
	@PostMapping(path ="save")
	public ResponseEntity<?> guardar(@RequestBody Mesero mesero) {
		
		try {
			if(meseroservimp.guardar(mesero).equals("idMeseroYaExiste"))
				return new ResponseEntity<String>("IdMesero ya existe error al guardar",HttpStatus.OK);
			else if(meseroservimp.guardar(mesero).equals("nombreYaExiste"))
				return new ResponseEntity<String>("El nombre ya existe error al guardar",HttpStatus.OK);
			else if(meseroservimp.guardar(mesero).equals("apellidoPaternoYaExiste"))
				return new ResponseEntity<String>("El apellido paterno ya existe error al guardar",HttpStatus.OK);
			else if(meseroservimp.guardar(mesero).equals("apellidoMaternoYaExiste"))
				return new ResponseEntity<String>("El apellido materno ya existe error al guardar",HttpStatus.OK);
			else
				return new ResponseEntity<Mesero>(mesero,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al guardar" +e.getMessage(),HttpStatus.OK);
			
		}
		
	}
	
	
	//http://localhost:9000/MeseroWebService/buscarXid
	@PostMapping(path ="buscarXid")
	public ResponseEntity<?> buscarXid(@RequestBody Mesero mesero){
		try {
			Mesero meseroEncontrado = meseroservimp.buscarXid(mesero.getIdMesero());
			if(meseroEncontrado != null)
				return new ResponseEntity<Mesero>(meseroEncontrado,HttpStatus.OK);
			else 
				return new ResponseEntity<String>("El registro que buscas no existe",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al buscar el registro" + e.getMessage(),HttpStatus.OK);

		}
				
			}
	
	
	//http://localhost:9000/MeseroWebService/editar
	@PutMapping(path ="editar")
	public ResponseEntity<?>editar(@RequestBody Mesero mesero){
		try {
			boolean meseroEditado = meseroservimp.editar(mesero);
				if(meseroEditado ==false)
					return new ResponseEntity<String>("El registro no existe, no se puede editar",HttpStatus.OK);
				else 
					return new ResponseEntity<Mesero>(mesero,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al editar el registro" + e.getMessage(),HttpStatus.OK);

		}
	}
	
	
	//http://localhost:9000/MeseroWebService/eliminar/{idMesero}
	@DeleteMapping(path="eliminar/{idMesero}")
	public ResponseEntity<?>eliminar(@PathVariable("idMesero")Integer idMesero){
		try {
			boolean meseroEliminar = meseroservimp.eliminar(idMesero);
			if(meseroEliminar == false)
				return new ResponseEntity<String>("El registro no existe, no se puede eliminar",HttpStatus.OK);
			else 
				return new ResponseEntity<String>("El registro se elimino",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error al eliminar el registro" + e.getMessage(),HttpStatus.OK);

		}
	}


}
