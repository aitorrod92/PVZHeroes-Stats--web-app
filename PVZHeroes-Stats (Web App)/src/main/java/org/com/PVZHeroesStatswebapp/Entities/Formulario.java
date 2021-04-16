package org.com.PVZHeroesStatswebapp.Entities;

public class Formulario {

	CartaAndCombobox CC1;
	CartaAndCombobox CC2;
	CartaAndCombobox CC3;

	ComboboxTipoUnion CTU1;
	ComboboxTipoUnion CTU2;

	public Formulario() {
		CC1 = new CartaAndCombobox();
		CC2 = new CartaAndCombobox();
		CC3 = new CartaAndCombobox();

		CTU1 = new ComboboxTipoUnion();
		CTU2 = new ComboboxTipoUnion();
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
	
	public ComboboxTipoUnion getCTU1() {
		return CTU1;
	}

	public void setCTU1(ComboboxTipoUnion cTU1) {
		CTU1 = cTU1;
	}

	public ComboboxTipoUnion getCTU2() {
		return CTU2;
	}

	public void setCTU2(ComboboxTipoUnion cTU2) {
		CTU2 = cTU2;
	}


}
