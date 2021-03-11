package org.com.PVZHeroesStatswebapp.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.com.PVZHeroesStatswebapp.Repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardsServiceImp implements CardsService {

	private CardsRepository cardsRepository;

	@Autowired
	public CardsServiceImp(CardsRepository theCardsRepository) {
		cardsRepository = theCardsRepository;
	}

	public ArrayList<Cartas> findAll() {
		return (ArrayList<Cartas>) cardsRepository.findAll();
	}

	public ArrayList<Cartas> findById(String id) {
		Cartas resultado = cardsRepository.findById(id).get();
		ArrayList<Cartas> arrayList = new ArrayList();
		arrayList.add(resultado);
		return arrayList;
	}

	@Override
	public ArrayList<Cartas> findByPattern(String valor, String Operador, String atributo) {
		return (ArrayList<Cartas>)cardsRepository.findByPattern(valor, Operador, atributo);
	}

	@Override
	public ArrayList<Cartas> findByValue(int valor, String Operador, String atributo) { 
		return (ArrayList<Cartas>)cardsRepository.findByValue(valor, Operador, atributo);

	}
}

