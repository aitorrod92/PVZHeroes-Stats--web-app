package org.com.PVZHeroesStatswebapp.Cards;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartas {

	@Id
	private String Nombre;
	private int Ataque;
	private int Defensa;
	
	protected Cartas() {}
			
	public Cartas(String nombre, int ataque, int defensa) {
		Nombre = nombre;
		Ataque = ataque;
		Defensa = defensa;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getAtaque() {
		return Ataque;
	}
	public void setAtaque(int ataque) {
		Ataque = ataque;
	}
	public int getDefensa() {
		return Defensa;
	}
	public void setDefensa(int defensa) {
		Defensa = defensa;
	}

	@Override
	public String toString() {
		return "Card [Nombre=" + Nombre + ", Ataque=" + Ataque + ", Defensa=" + Defensa + "]";
	}	
}
