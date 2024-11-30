package com.mx.SistemaComedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.SistemaComedor.dao.MesasDao;
import com.mx.SistemaComedor.dao.MeserosDao;
import com.mx.SistemaComedor.model.Mesas;

@Service
public class MesasServImp {

	@Autowired
	MesasDao mesasDao;

	@Autowired
	MeserosDao meserosDao;

	// ---LISTAR
	@Transactional(readOnly = true)
	public List<Mesas> listar() {
		return mesasDao.findAll();
	}

	// ---GUARDAR(VALIDAR QUE EL ID Y NUM_MESA NO SE REPITA, ID_MESERO EXISTA)
	@Transactional
	public String guardar(Mesas mesa) {
		if (!meserosDao.existsById(mesa.getMesero().getId())) {
			return "No existe el mesero.";
		}
		if (mesasDao.existsById(mesa.getId())) {
			return "Esta mesa ya está registrada.";
		}

		if (mesasDao.findByNumMesa(mesa.getNumMesa()).isPresent()) {
			return "Este número de mesa ya está registrado";
		}

		mesasDao.save(mesa);
		return "Se ha guardado exitosamente.";
	}

	// ---BUSCAR
	@Transactional
	public Mesas buscar(Long id) {
		return mesasDao.findById(id).orElseGet(null);
	}

	// ---EDITAR(VALIDAR QUE EL ID EXISTA, ID_MESERO EXISTA)
	@Transactional
	public String editar(Mesas mesa) {
		if (!meserosDao.existsById(mesa.getMesero().getId())) {
			return "No existe el mesero.";
		}
		if (!mesasDao.existsById(mesa.getId())) {
			return "Esta mesa no está registrada.";
		}
		mesasDao.save(mesa);
		return "Se ha editado exitosamente.";

	}

	// ---ELIMINAR(VALIDAR QUE EL ID EXISTA)
	@Transactional
	public boolean eliminar(Mesas mesa) {
		if (mesasDao.existsById(mesa.getId())) {
			mesasDao.delete(mesa);
			return true;
		}
		return false;
	}

}
