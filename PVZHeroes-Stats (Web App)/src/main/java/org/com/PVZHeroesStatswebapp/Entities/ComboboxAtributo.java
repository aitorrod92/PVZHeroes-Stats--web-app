package org.com.PVZHeroesStatswebapp.Entities;

import javax.persistence.Id;

public class ComboboxAtributo extends ComboboxGenerico {

	@Id
	protected String valor;
	
	public ComboboxAtributo() {	
		generarOpcionesSeleccion();
	}

	public ComboboxAtributo(String valor) {
		generarOpcionesSeleccion();
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	private void generarOpcionesSeleccion() {
		opcionesSeleccion.put("Nombre", "Nombre");
		opcionesSeleccion.put("Ataque", "Ataque");
		opcionesSeleccion.put("Defensa", "Defensa");
		opcionesSeleccion.put("Coste", "Coste");
		opcionesSeleccion.put("Clase", "Clase");
		opcionesSeleccion.put("Tribus", "Tribus");
		opcionesSeleccion.put("Atributos", "Atributos");
		opcionesSeleccion.put("Habilidades", "Habilidades");
		opcionesSeleccion.put("Rareza", "Rareza");
		opcionesSeleccion.put("Mazo", "Mazo");
		opcionesSeleccion.put("Tipo", "Tipo");
		opcionesSeleccion.put("NumeroAtributos", "NumeroAtributos");
	}
}
