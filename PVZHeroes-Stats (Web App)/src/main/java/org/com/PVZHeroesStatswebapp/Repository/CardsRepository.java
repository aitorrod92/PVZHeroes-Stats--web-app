package org.com.PVZHeroesStatswebapp.Repository;

import java.util.ArrayList;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardsRepository extends JpaRepository<Cartas, String> {

	@Query("SELECT u FROM Cartas u WHERE u.Nombre LIKE %:id%")
	ArrayList<Cartas> findByPatternIdLIKE(@Param("id") String id);
	
	@Query("SELECT u FROM Cartas u WHERE u.Nombre NOT LIKE %:id%") 
	ArrayList<Cartas> findByPatternIdNOTLIKE(@Param("id") String id);
	
	@Query("SELECT u FROM Cartas u WHERE u.Ataque > :valor") 
	ArrayList<Cartas> findByValueGreaterThan(@Param("valor") int valor);
	
	@Query("SELECT u FROM Cartas u WHERE u.Ataque = :valor") 
	ArrayList<Cartas> findByValueEqualTo(@Param("valor") int valor);
	
	@Query("SELECT u FROM Cartas u WHERE u.Ataque < :valor") 
	ArrayList<Cartas> findByValueLessThan(@Param("valor") int valor);
}

