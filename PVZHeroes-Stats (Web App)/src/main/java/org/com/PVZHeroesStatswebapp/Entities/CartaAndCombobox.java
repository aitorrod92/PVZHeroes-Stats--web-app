package org.com.PVZHeroesStatswebapp.Entities;

public class CartaAndCombobox {
	private Cartas carta;
	
	private ComboboxOperadores comboboxOperadores; 
	private ComboboxAtributo comboboxA;
	private ComboboxTipoUnion comboboxTipoUnion;
	

	public CartaAndCombobox() {
		carta = new Cartas();
		this.comboboxOperadores = new ComboboxOperadores();
		this.comboboxA = new ComboboxAtributo();
		this.comboboxTipoUnion = new ComboboxTipoUnion();
	}


	public Cartas getCarta() {
		return carta;
	}

	public void setCarta(Cartas carta) {
		this.carta = carta;
	}

	
	public ComboboxOperadores getComboboxOperadores() {
		return comboboxOperadores;
	}

	public void setComboboxOperadores(ComboboxOperadores comboboxOperadores) {
		this.comboboxOperadores = comboboxOperadores;
	}


	public ComboboxAtributo getComboboxA() {
		return comboboxA;
	}

	public void setComboboxA(ComboboxAtributo comboboxA) {
		this.comboboxA = comboboxA;
	}
	
	public ComboboxTipoUnion getComboboxTipoUnion() {
		return comboboxTipoUnion;
	}

	public void setComboboxTipoUnion(ComboboxTipoUnion comboboxTipoUnion) {
		this.comboboxTipoUnion = comboboxTipoUnion;
	}
	
	
	
}
