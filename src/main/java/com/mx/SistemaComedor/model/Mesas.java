package com.mx.SistemaComedor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 CREATE TABLE MESAS(
ID NUMBER PRIMARY KEY,
NUM_MESA NUMBER NOT NULL,
NUM_SILLAS NUMBER NOT NULL,
ID_MESERO NUMBER NOT NULL,
FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
);*/

@Entity
@Table(name = "MESAS")
public class Mesas {
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NUM_MESA")
	private Long numMesa;
	@Column(name = "NUM_SILLAS")
	private Long numSillas;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MESERO")
	Meseros mesero;

	public Mesas() {
	}

	public Mesas(Long id, Long numMesa, Long numSillas, Meseros mesero) {
		this.id = id;
		this.numMesa = numMesa;
		this.numSillas = numSillas;
		this.mesero = mesero;
	}

	@Override
	public String toString() {
		return "Mesas [id=" + id + ", numMesa=" + numMesa + ", numSillas=" + numSillas + ", mesero=" + mesero + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Long numMesa) {
		this.numMesa = numMesa;
	}

	public Long getNumSillas() {
		return numSillas;
	}

	public void setNumSillas(Long numSillas) {
		this.numSillas = numSillas;
	}

	public Meseros getMesero() {
		return mesero;
	}

	public void setMesero(Meseros mesero) {
		this.mesero = mesero;
	}
	
	
}
