package org.com.PVZHeroesStatswebapp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.com.PVZHeroesStatswebapp.Entities.CartaAndCombobox;
import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.com.PVZHeroesStatswebapp.Entities.Combobox;
import org.com.PVZHeroesStatswebapp.Service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	HashMap<String, String> OpcionesSelección = new HashMap<String, String>();

	@Autowired
	private CardsService cardsService;

	@GetMapping("/index")
	public String mostrarPaginaInicial(Model theModel) {
		añadirListadoCompletoAlModelo(theModel);
		añadirElementos(theModel);
		return "index";
	}

	@RequestMapping("/busqueda")
	public String mostrarPaginaBusqueda(@ModelAttribute("combinaciónCartaCombobox") CartaAndCombobox cartaAndCombobox,
			Model theModel) {
		String valorCombo = cartaAndCombobox.getCombobox().getValor();
		String nombreCarta = cartaAndCombobox.getCarta().getNombre().trim();
		try {
			if (nombreCarta.equals("")) {
				añadirListadoCompletoAlModelo(theModel);
				return "index";
			} else {
				ArrayList<Cartas> cartasRecuperadas = new ArrayList();
				switch (valorCombo) {
				case "==":
					Cartas cartaRecuperada = cardsService.findById(nombreCarta);
					theModel.addAttribute("cartaBuscada", cartaRecuperada);
					return "busqueda";
				case "LIKE":
					cartasRecuperadas = cardsService.findByPatternId(nombreCarta, true);
					añadirElementos(theModel);
					theModel.addAttribute("cards", cartasRecuperadas);
					return "index"; // TEMPORAL
				case "NOT LIKE":
					cartasRecuperadas = cardsService.findByPatternId(nombreCarta, false);
					añadirElementos(theModel);
					theModel.addAttribute("cards", cartasRecuperadas);
					return "index";
				default:
					return null;
				}
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
		
	private void añadirElementos(Model theModel) {
		Cartas cartaBuscada = añadirCartaBuscada(theModel);
		Combobox combobox = añadirCombobox(theModel);
		// Para poder pasar dos atributos en el formulario, estos se tienen que poner en
		// un objeto común
		theModel.addAttribute("combinacionCartaCombobox", new CartaAndCombobox(cartaBuscada, combobox));
	}

	private Combobox añadirCombobox(Model theModel) {
		Combobox combobox = new Combobox();
		theModel.addAttribute("combobox", combobox);
		return combobox;
	}

	private Cartas añadirCartaBuscada(Model theModel) {
		Cartas cartaBuscada = new Cartas();
		theModel.addAttribute("cartaBuscada", cartaBuscada);
		return cartaBuscada;
	}

	
	
}
