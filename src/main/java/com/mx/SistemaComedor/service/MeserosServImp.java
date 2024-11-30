package com.mx.SistemaComedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.SistemaComedor.dao.MeserosDao;
import com.mx.SistemaComedor.model.Meseros;

@Service
public class MeserosServImp {
	@Autowired
	MeserosDao meseroDao;

	// LISTAR
	@Transactional(readOnly = true)
	public List<Meseros> listar() {
		return meseroDao.findAll();
	}

	// GUARDAR (VALIDAR QUE EL ID Y NOMBRECOMPLETO NO SE REPITA)
	@Transactional
	public String guardar(Meseros mesero) {
		if (meseroDao.existsById(mesero.getId())) {
			return "El id ya existe";
		}

		if (meseroDao.existsByNombreCompleto(mesero.getNombre(), mesero.getApp(), mesero.getApm())) {
			return "Este nombre ya está registrado";
		}

		meseroDao.save(mesero);
		return "Guardado con éxito en la base de datos.";

	}

	// ---BUSCAR
	@Transactional(readOnly = true)
	public Meseros buscar(Long id) {
	    return meseroDao.findById(id).orElse(null);
	}

	// ---EDITAR(VALIDAR QUE EL ID EXISTA)
	@Transactional
	public boolean editar(Meseros mesero) {
		if(meseroDao.existsById(mesero.getId())) {
			meseroDao.save(mesero);
			return true;
		}
		return false;
	}
	
	// ---ELIMINAR(VALIDAR QUE EL ID EXISTA)
	
	@Transactional
	public boolean eliminar(Meseros mesero) {
		if(meseroDao.existsById(mesero.getId())) {
			meseroDao.delete(mesero);
			return true;
		}
		return false;
	}
	
}
