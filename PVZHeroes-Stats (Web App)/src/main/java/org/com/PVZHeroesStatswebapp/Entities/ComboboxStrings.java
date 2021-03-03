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
		opcionesSeleccion.put("Es exactamente", "==");
		opcionesSeleccion.put("Incluye", "LIKE");
		opcionesSeleccion.put("No incluye", "NOT LIKE");
	}
	
	public ComboboxStrings(String valor) {
		opcionesSeleccion.put("Es exactamente", "==");
		opcionesSeleccion.put("Incluye", "LIKE");
		opcionesSeleccion.put("No incluye", "NOT LIKE");
		this.valor = valor;
	}
	
}
