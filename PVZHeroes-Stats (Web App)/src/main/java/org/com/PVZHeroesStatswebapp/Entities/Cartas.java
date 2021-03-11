package org.com.PVZHeroesStatswebapp.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cartas {

	@Id
	private String Nombre;
	private int Ataque;
	private int Defensa;
	private int Coste;
	private String Clase;
	private String Tribus;
	private String Atributos;
	private String Habilidades;
	private String Rareza;
	private String Mazo;
	private String URL;
	private String Tipo;
	private int NumeroAtributos;
	private String Imagen;
	
	private String Valor;
		
	public Cartas(String nombre, int ataque, int defensa, int coste, String clase, String tribus, String atributos,
			String habilidades, String rareza, String mazo, String tipo, String uRL, int numeroAtributos, String imagen) {
		Nombre = nombre;
		Ataque = ataque;
		Defensa = defensa;
		Coste = coste;
		Clase = clase;
		Tribus = tribus;
		Atributos = atributos;
		Habilidades = habilidades;
		Rareza = rareza;
		Mazo = mazo;
		Tipo = tipo;
		URL = uRL;
		NumeroAtributos = numeroAtributos;
		Imagen = imagen;
	}


	public Cartas() {}

	
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
	
	public int getCoste() {
		return Coste;
	}

	public void setCoste(int coste) {
		Coste = coste;
	}

	public String getClase() {
		return Clase;
	}

	public void setClase(String clase) {
		Clase = clase;
	}

	public String getTribus() {
		return Tribus;
	}

	public void setTribus(String tribus) {
		Tribus = tribus;
	}

	public String getAtributos() {
		return Atributos;
	}

	public void setAtributos(String atributos) {
		Atributos = atributos;
	}

	public String getHabilidades() {
		return Habilidades;
	}

	public void setHabilidades(String habilidades) {
		Habilidades = habilidades;
	}

	public String getRareza() {
		return Rareza;
	}

	public void setRareza(String rareza) {
		Rareza = rareza;
	}

	public String getMazo() {
		return Mazo;
	}

	public void setMazo(String mazo) {
		Mazo = mazo;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getNumeroAtributos() {
		return NumeroAtributos;
	}

	public void setNumeroAtributos(int numeroAtributos) {
		NumeroAtributos = numeroAtributos;
	}
	
	public String getImagen() {
		return Imagen;
	}
	public void setImagen(String imagen) {
		Imagen = imagen;
	}
	
	public String getValor() {
		return this.Valor;
	}

	public void setValor(String valor) {
		this.Valor = valor;
	}



}
