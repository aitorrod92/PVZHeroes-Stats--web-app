package org.com.PVZHeroesStatswebapp.Entities;

public class CartaAndCombobox {
	private Cartas carta;
	private ComboboxStrings combobox;
	private ComboboxNumerico comboboxN;
	private ComboboxAtributo comboboxA;
	
	public CartaAndCombobox() {	}
	
	public CartaAndCombobox(Cartas carta, ComboboxStrings combobox) {
		this.carta = carta;
		this.combobox = combobox;
	}
	
	public CartaAndCombobox(Cartas carta, ComboboxNumerico comboboxN) {
		this.carta = carta;
		this.comboboxN = comboboxN;
	}
		
	public CartaAndCombobox(Cartas carta, ComboboxAtributo comboboxA) {
		this.carta = carta;
		this.comboboxA = comboboxA;
	}

	public Cartas getCarta() {
		return carta;
	}

	public void setCarta(Cartas carta) {
		this.carta = carta;
	}

	public ComboboxStrings getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboboxStrings combobox) {
		this.combobox = combobox;
	}	
	
	public ComboboxNumerico getComboboxN() {
		return comboboxN;
	}

	public void setComboboxN(ComboboxNumerico comboboxN) {
		this.comboboxN = comboboxN;
	}

	public ComboboxAtributo getComboboxA() {
		return comboboxA;
	}

	public void setComboboxA(ComboboxAtributo comboboxA) {
		this.comboboxA = comboboxA;
	}
	
	
	
}
