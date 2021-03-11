package org.com.PVZHeroesStatswebapp.Service;

import java.util.ArrayList;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;

public interface CardsService {

	public ArrayList<Cartas> findAll();
	
	public ArrayList<Cartas> findById(String id);
	
	public ArrayList<Cartas> findByPatternId(String id, Boolean LIKE);
	
	public ArrayList<Cartas> findByValue(int valor, String Operador, String atributo);

}
