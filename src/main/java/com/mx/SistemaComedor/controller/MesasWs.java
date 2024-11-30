package com.mx.SistemaComedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.SistemaComedor.model.Mesas;
import com.mx.SistemaComedor.service.MesasServImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin
@RequestMapping("MesasWs")
public class MesasWs {

	@Autowired
	MesasServImp mesasImp;

	// http://localhost:9000/MesasWs/listar
	@GetMapping("listar")
	public List<Mesas> listar() {
		return mesasImp.listar();
	}

	// http://localhost:9000/MesasWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<String> guardar(@RequestBody Mesas mesa) {
		String mensaje = mesasImp.guardar(mesa);

		switch (mensaje) {
		case "No existe el mesero.":
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		case "Esta mesa ya está registrada.":
		case "Este número de mesa ya está registrado":
			return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
		case "Se ha guardado exitosamente.":
			return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
		default:
			return new ResponseEntity<>("Ocurrió un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:9000/MesasWs/buscar
	@PostMapping("buscar")
	public Mesas postMethodName(@RequestBody Mesas mesa) {
		return mesasImp.buscar(mesa.getId());
	}

	// http://localhost:9000/MesasWs/editar
	@PostMapping("editar")
	public ResponseEntity<String> editar(@RequestBody Mesas mesa) {
		String mensaje = mesasImp.editar(mesa);
		switch (mensaje) {
		case "No existe el mesero.":
			return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
		case "Esta mesa no está registrada.":
			return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
		case "Se ha editado exitosamente.":
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		default:
			return new ResponseEntity<>("Ocurrió un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	// http://localhost:9000/MesasWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Mesas mesa) {
		boolean response = mesasImp.eliminar(mesa);
		if (response) {
			return new ResponseEntity<>("Se eliminó de forma correcta.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No existe esa mesa.", HttpStatus.CONFLICT);
		}
	}

}
