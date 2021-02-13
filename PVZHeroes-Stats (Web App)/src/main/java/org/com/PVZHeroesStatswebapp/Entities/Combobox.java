package org.com.PVZHeroesStatswebapp.Entities;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Combobox {

	@Id
	private String valor;
	private HashMap<String, String> opcionesSeleccion = new HashMap<String, String>();

	public Combobox() {
		opcionesSeleccion.put("Es exactamente", "==");
		opcionesSeleccion.put("Incluye", "LIKE");
		opcionesSeleccion.put("No incluye", "NOT LIKE");
	}
	
	public Combobox(String valor) {
		opcionesSeleccion.put("Incluye", "LIKE");
		opcionesSeleccion.put("No incluye", "NOT LIKE");
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public HashMap<String, String> getOpcionesSeleccion() {
		return opcionesSeleccion;
	}

	public void setOpcionesSeleccion(HashMap<String, String> opcionesSeleccion) {
		this.opcionesSeleccion = opcionesSeleccion;
	}
}
