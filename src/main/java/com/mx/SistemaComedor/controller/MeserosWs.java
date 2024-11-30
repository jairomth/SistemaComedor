package com.mx.SistemaComedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.SistemaComedor.model.Meseros;
import com.mx.SistemaComedor.service.MeserosServImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "MeserosWs")
@CrossOrigin
public class MeserosWs {

	@Autowired
	MeserosServImp meseroImp;

	// http://localhost:9000/MeserosWs/listar
	@GetMapping("listar")
	public List<Meseros> listar() {
		return meseroImp.listar();
	}

	// http://localhost:9000/MeserosWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<String> postMethodName(@RequestBody Meseros mesero) {
		String mensaje = meseroImp.guardar(mesero);
		switch (mensaje) {
		case "El id ya existe":
			return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
		case "Este nombre ya está registrado":
			return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
		case "Guardado con éxito en la base de datos.":
			return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
		default:
			return new ResponseEntity<>("Ocurrió un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// http://localhost:9000/MeserosWs/buscar
	@PostMapping("buscar")
	public Meseros buscar(@RequestBody Meseros mesero) {
		return meseroImp.buscar(mesero.getId());
	}

	// http://localhost:9000/MeserosWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Meseros mesero) {
		boolean response = meseroImp.editar(mesero);

		if (response) {
			return new ResponseEntity<>(mesero, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El id no existe.", HttpStatus.NOT_FOUND);
		}
	}

	// http://localhost:9000/MeserosWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Meseros mesero) {
		boolean response = meseroImp.eliminar(mesero);

		if (response) {
			return new ResponseEntity<>("Se eliminó exitosamente de la base de datos.", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El id no existe.", HttpStatus.NOT_FOUND);
		}
	}

}
