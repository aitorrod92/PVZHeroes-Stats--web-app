package org.com.PVZHeroesStatswebapp.Entities;

import java.util.HashMap;

import javax.persistence.Id;

public class ComboboxOperadores {

	@Id
	protected String valor;
	
	private HashMap <String, String> opcionesSeleccion = new HashMap();
		
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = opcionesSeleccion.get(valor);
	}
	
	public ComboboxOperadores() {
		generarOpcionesSeleccion();
	}
	
	public ComboboxOperadores(String valor) {
		this.valor = valor;
	}
	
	private void generarOpcionesSeleccion() {
		opcionesSeleccion.put("Es exactamente", "==");
		opcionesSeleccion.put("Incluye", "LIKE");
		opcionesSeleccion.put("No incluye", "NOT LIKE");
		
		opcionesSeleccion.put(">", ">");
		opcionesSeleccion.put("=", "=");
		opcionesSeleccion.put("<", "<");
		opcionesSeleccion.put(">=", ">=");
		opcionesSeleccion.put("<=", "<=");
	}
}
