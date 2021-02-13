package org.com.PVZHeroesStatswebapp.Controller;


import java.util.ArrayList;

import org.com.PVZHeroesStatswebapp.Cards.Cartas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/index")
	public String showHelloWorld(Model theModel) {	
		Cartas carta1 = new Cartas("Carta 1", 1, 1);
		Cartas carta2 = new Cartas("Carta 2", 2, 1);
		Cartas carta3 = new Cartas("Carta 3", 4, 5);
		ArrayList<Cartas> Cards = new ArrayList();
		Cards.add(carta1);
		Cards.add(carta2);
		Cards.add(carta3);
		theModel.addAttribute("cards",Cards);
		return "helloworld";
	}	

}
