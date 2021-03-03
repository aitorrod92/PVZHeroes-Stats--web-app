package org.com.PVZHeroesStatswebapp.Entities;

import javax.persistence.Id;

public class ComboboxNumerico extends ComboboxGenerico {

	@Id
	protected String valor;
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public ComboboxNumerico() {
		generarOpcionesSeleccion();
	}
	
	public ComboboxNumerico(String valor) {
		generarOpcionesSeleccion();
		this.valor = valor;
	}
	
	private void generarOpcionesSeleccion() {
		opcionesSeleccion.put(">", ">");
		opcionesSeleccion.put("=", "=");
		opcionesSeleccion.put("<", "<");
		opcionesSeleccion.put(">=", ">=");
		opcionesSeleccion.put("<=", "<=");
	}
	
}
