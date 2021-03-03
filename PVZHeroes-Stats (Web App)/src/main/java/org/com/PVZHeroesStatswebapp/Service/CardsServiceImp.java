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

	public Cartas findById(String id) {
		Optional<Cartas> resultado = cardsRepository.findById(id);
		return resultado.get();
	}

	@Override
	public ArrayList<Cartas> findByPatternId(String id, Boolean LIKE) {
		if (LIKE) {
			return (ArrayList<Cartas>) cardsRepository.findByPatternIdLIKE(id);
		} else {
			return (ArrayList<Cartas>) cardsRepository.findByPatternIdNOTLIKE(id);
		}
	}

	@Override
	public ArrayList<Cartas> findByValue(int valor, String Operador) { 
		// NECESARIO MODIFICARLO PARA QUE SE PASEN OTROS ATRIBUTOS EN FUNCIÓN DE LO SELECCIONADO
		return (ArrayList<Cartas>)cardsRepository.findByValue(valor, Operador, "Ataque");

	}
}

