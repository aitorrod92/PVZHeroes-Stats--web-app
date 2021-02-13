package org.com.PVZHeroesStatswebapp.Service;

import java.util.ArrayList;
import org.com.PVZHeroesStatswebapp.Cards.Cartas;

public interface CardsService {

	public ArrayList<Cartas> findAll();
	
	public Cartas findById(String id);
}
