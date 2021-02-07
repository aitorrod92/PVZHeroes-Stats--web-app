package org.com.PVZHeroesStatswebapp.Controller;

import java.util.ArrayList;

import org.com.PVZHeroesStatswebapp.Cards.Cartas;
import org.com.PVZHeroesStatswebapp.Service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	@Autowired
	private CardsService cardsService;
	
	@GetMapping("/index")
	public String mostrarPaginaInicial(Model theModel) {	
		ArrayList<Cartas> cartas = cardsService.findAll();
		Cartas carta = new Cartas();
		theModel.addAttribute("cards", cartas);
		theModel.addAttribute("cartaBuscada", carta);
		return "index";
	}	
	

}
