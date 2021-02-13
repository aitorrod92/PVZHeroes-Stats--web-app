package org.com.PVZHeroesStatswebapp.Controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.com.PVZHeroesStatswebapp.Cards.Cartas;
import org.com.PVZHeroesStatswebapp.Service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired
	private CardsService cardsService;

	@GetMapping("/index")
	public String mostrarPaginaInicial(Model theModel) {
		añadirListadoCompletoAlModelo(theModel);
		theModel.addAttribute("cartaBuscada", new Cartas());
		return "index";
	}

	@RequestMapping("/busqueda")
	public String mostrarPaginaBusqueda(@ModelAttribute("cartaBuscada") Cartas cartaBuscada,
		 Model theModel) {
		String nombreCarta = cartaBuscada.getNombre().trim();
		try {
			if (nombreCarta.equals("")) {
				añadirListadoCompletoAlModelo(theModel);
				return "index";
			} else {
				Cartas cartaRecuperada = cardsService.findById(nombreCarta);
				theModel.addAttribute("cartaBuscada", cartaRecuperada);
				return "busqueda";
			}
		} catch (NoSuchElementException ex) {
			theModel.addAttribute("nombreCarta", nombreCarta);
			return "busquedaFallida";
		}
	}

	private void añadirListadoCompletoAlModelo(Model theModel) {
		ArrayList<Cartas> cartas = cardsService.findAll();
		theModel.addAttribute("cards", cartas);
	}
}
