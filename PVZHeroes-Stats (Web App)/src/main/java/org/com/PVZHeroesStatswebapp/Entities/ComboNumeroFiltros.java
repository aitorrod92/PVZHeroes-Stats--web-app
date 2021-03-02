package org.com.PVZHeroesStatswebapp.Entities;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ComboNumeroFiltros {
	@Id
	private String valor;
	private HashMap<String, Integer> opcionesSeleccion = new HashMap<String, Integer>();

	public ComboNumeroFiltros() {
		opcionesSeleccion.put("1", 1);
		opcionesSeleccion.put("2", 2);
		opcionesSeleccion.put("3", 3);
		opcionesSeleccion.put("4", 4);
		opcionesSeleccion.put("5", 5);
		opcionesSeleccion.put("6", 6);
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public HashMap<String, Integer> getOpcionesSeleccion() {
		return opcionesSeleccion;
	}

}
