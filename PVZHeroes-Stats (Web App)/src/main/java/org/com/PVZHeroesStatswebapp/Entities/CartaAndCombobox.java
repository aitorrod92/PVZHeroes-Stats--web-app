package org.com.PVZHeroesStatswebapp.Entities;

public class CartaAndCombobox {

	private Cartas carta;
	private Combobox combobox;
	
	public CartaAndCombobox() {

	}
	
	public CartaAndCombobox(Cartas carta, Combobox combobox) {
		this.carta = carta;
		this.combobox = combobox;
	}
		
	public Cartas getCarta() {
		return carta;
	}

	public void setCarta(Cartas carta) {
		this.carta = carta;
	}

	public Combobox getCombobox() {
		return combobox;
	}

	public void setCombobox(Combobox combobox) {
		this.combobox = combobox;
	}	

}
