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
		opcionesSeleccion.put(">", ">");
		opcionesSeleccion.put("=", "=");
		opcionesSeleccion.put("<", "<");
	}
	
	public ComboboxNumerico(String valor) {
		opcionesSeleccion.put(">", ">");
		opcionesSeleccion.put("=", "=");
		opcionesSeleccion.put("<", "<");
		this.valor = valor;
	}
	
}
