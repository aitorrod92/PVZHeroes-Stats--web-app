package org.com.PVZHeroesStatswebapp.Entities;

public class Formulario {

	CartaAndCombobox CC1;
	CartaAndCombobox CC2;
	CartaAndCombobox CC3;

	public Formulario() {
		CC1 = new CartaAndCombobox();
		CC2 = new CartaAndCombobox();
		CC3 = new CartaAndCombobox();
	}

	public CartaAndCombobox getCC1() {
		return CC1;
	}

	public void setCC1(CartaAndCombobox cC1) {
		CC1 = cC1;
	}

	public CartaAndCombobox getCC2() {
		return CC2;
	}

	public void setCC2(CartaAndCombobox cC2) {
		CC2 = cC2;
	}

	public CartaAndCombobox getCC3() {
		return CC3;
	}

	public void setCC3(CartaAndCombobox cC3) {
		CC3 = cC3;
	}

}
