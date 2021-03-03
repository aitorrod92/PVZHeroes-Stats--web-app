package org.com.PVZHeroesStatswebapp.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ComboboxStrings extends ComboboxGenerico {

	@Id
	protected String valor;
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public ComboboxStrings() {
		generarOpcionesSeleccion();
	}
	
	public ComboboxStrings(String valor) {
		generarOpcionesSeleccion();
		this.valor = valor;
	}
	
	private void generarOpcionesSeleccion() {
		opcionesSeleccion.put("Es exactamente", "==");
		opcionesSeleccion.put("Incluye", "LIKE");
		opcionesSeleccion.put("No incluye", "NOT LIKE");
	}
	
}
