package org.com.PVZHeroesStatswebapp.Entities;

import java.util.HashMap;
import javax.persistence.Id;

public class ComboboxGenerico {

	protected HashMap<String, String> opcionesSeleccion = new HashMap<String, String>();
	
	public HashMap<String, String> getOpcionesSeleccion() {
		return opcionesSeleccion;
	}

	public void setOpcionesSeleccion(HashMap<String, String> opcionesSeleccion) {
		this.opcionesSeleccion = opcionesSeleccion;
	}
	
}
