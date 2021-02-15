package org.com.PVZHeroesStatswebapp.Entities;

public class ConjuntoDeCombosYCartas {

	private CartaAndCombobox GrupoNombre;
	private CartaAndCombobox GrupoAtaque;
		
	public ConjuntoDeCombosYCartas(CartaAndCombobox grupoNombre, CartaAndCombobox grupoAtaque) {
		GrupoNombre = grupoNombre;
		GrupoAtaque = grupoAtaque;
	}
	public CartaAndCombobox getGrupoNombre() {
		return GrupoNombre;
	}
	public void setGrupoNombre(CartaAndCombobox grupoNombre) {
		GrupoNombre = grupoNombre;
	}
	public CartaAndCombobox getGrupoAtaque() {
		return GrupoAtaque;
	}
	public void setGrupoAtaque(CartaAndCombobox grupoAtaque) {
		GrupoAtaque = grupoAtaque;
	}	
}
