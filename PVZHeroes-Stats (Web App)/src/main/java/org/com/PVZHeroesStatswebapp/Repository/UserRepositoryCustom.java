package org.com.PVZHeroesStatswebapp.Repository;

import java.util.ArrayList;
import java.util.List;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;

public interface UserRepositoryCustom {

	List<Cartas> findByValue(int value, String operator, String atributo);
	
	List<Cartas> findByPattern(String value, String operator, String atributo);
		
}
