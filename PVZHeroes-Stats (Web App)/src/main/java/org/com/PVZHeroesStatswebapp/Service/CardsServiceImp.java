package org.com.PVZHeroesStatswebapp.Service;

import java.util.ArrayList;

import org.com.PVZHeroesStatswebapp.Cards.Cartas;
import org.com.PVZHeroesStatswebapp.Repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardsServiceImp implements CardsService {

	private CardsRepository cardsRepository;
	
	@Autowired
	public CardsServiceImp (CardsRepository theCardsRepository) {
		cardsRepository = theCardsRepository;
	}
	
	public ArrayList<Cartas> findAll(){
		return (ArrayList<Cartas>) cardsRepository.findAll();
	}
}
