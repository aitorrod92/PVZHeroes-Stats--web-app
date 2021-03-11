package org.com.PVZHeroesStatswebapp.Repository;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Cartas, String>, UserRepositoryCustom {

}

