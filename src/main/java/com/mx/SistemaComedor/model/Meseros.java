package com.mx.SistemaComedor.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*CREATE TABLE MESERO(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(70) NOT NULL,
APP VARCHAR2(70) NOT NULL,
APM VARCHAR2(70),
SUELDO NUMBER NOT NULL
);*/

@Entity
@Table(name = "MESERO")
public class Meseros {
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APP")
	private String app;

	@Column(name = "APM")
	private String apm;

	@Column(name = "SUELDO")
	private Float sueldo;

	@OneToMany(mappedBy = "mesero", cascade = CascadeType.ALL)
	List<Mesas> lista = new ArrayList<>();

	public Meseros() {
	}

	public Meseros(Long id, String nombre, String app, String apm, Float sueldo) {
		this.id = id;
		this.nombre = nombre;
		this.app = app;
		this.apm = apm;
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Meseros [id=" + id + ", nombre=" + nombre + ", app=" + app + ", apm=" + apm + ", sueldo=" + sueldo
				+ "]\n";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getApm() {
		return apm;
	}

	public void setApm(String apm) {
		this.apm = apm;
	}

	public Float getSueldo() {
		return sueldo;
	}

	public void setSueldo(Float sueldo) {
		this.sueldo = sueldo;
	}

}
