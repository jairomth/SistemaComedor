package com.mx.SistemaComedor.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.SistemaComedor.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long>{
	
	public Optional<Mesas> findByNumMesa(Long numMesa);
}
