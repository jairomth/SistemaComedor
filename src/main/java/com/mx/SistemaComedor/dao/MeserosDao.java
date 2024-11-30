package com.mx.SistemaComedor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.SistemaComedor.model.Meseros;

public interface MeserosDao extends JpaRepository<Meseros, Long>{
	
	@Query("SELECT COUNT(m) > 0 FROM Meseros m WHERE m.nombre = :nombre AND m.app = :app AND m.apm = :apm")
	boolean existsByNombreCompleto(@Param("nombre") String nombre, @Param("app") String app, @Param("apm") String apm);

}
