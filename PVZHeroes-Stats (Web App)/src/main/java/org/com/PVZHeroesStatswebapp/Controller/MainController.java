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

	String[] listaPosiblesImagenes = { "strength", "suns", "brains", "freeze", "health", "bullseye", "double strike",
			"strikethrough", "deadly", "untrickable", "overshoot", "frenzy"};

	@Autowired
	private CardsService cardsService;

	@GetMapping("/index")
	public String mostrarPaginaInicial(Model theModel) {
		añadirListadoCompletoAlModelo(theModel);
		theModel.addAttribute("listaPosiblesImagenes", listaPosiblesImagenes);
		añadirElementos(theModel);
		return "index";
	}

	@RequestMapping("/busqueda")
	public String mostrarPaginaBusqueda(@ModelAttribute("combinaciónCartaCombobox") CartaAndCombobox cartaAndCombobox,
			Model theModel) {
		String valorCombo = cartaAndCombobox.getCombobox().getValor();
		String nombreCarta = cartaAndCombobox.getCarta().getNombre().trim();
		añadirElementos(theModel);
		ArrayList<Cartas> cartasRecuperadas = new ArrayList();
		try {
			if (nombreCarta.equals("")) {
				cartasRecuperadas = cardsService.findAll();
				return devolverBusquedaConLista(theModel, cartasRecuperadas);
			} else {
				switch (valorCombo) {
				case "==":
				case "":
					return devolverBusquedaMonoResultado(theModel, nombreCarta);
				case "LIKE":
					cartasRecuperadas = cardsService.findByPatternId(nombreCarta, true);
					return devolverBusquedaOBusquedaFallida(theModel, nombreCarta, cartasRecuperadas);
				case "NOT LIKE":
					cartasRecuperadas = cardsService.findByPatternId(nombreCarta, false);
					return devolverBusquedaOBusquedaFallida(theModel, nombreCarta, cartasRecuperadas);
				default:
					return null;
				}
			}
		} catch (NoSuchElementException ex) {
			return devolverBusquedaFallida(theModel, nombreCarta);
		}
	}

	private String devolverBusquedaMonoResultado(Model theModel, String nombreCarta) {
		Cartas cartaRecuperada = cardsService.findById(nombreCarta);
		theModel.addAttribute("cartaBuscada", cartaRecuperada);
		return "busqueda";
	}

	private String devolverBusquedaConLista(Model theModel, ArrayList<Cartas> listaCartas) {
		theModel.addAttribute("listaCartas", listaCartas);
		return "index";
	}

	private void añadirListadoCompletoAlModelo(Model theModel) {
		ArrayList<Cartas> cartas = cardsService.findAll();
		for (Cartas carta : cartas) {
			String habilidades = carta.getHabilidades();
			for (String nombreImagen : listaPosiblesImagenes) {
				if (habilidades.contains(nombreImagen)) {
					int localizaciónImagen = habilidades.indexOf(nombreImagen);
					int longitudNombreImagen = nombreImagen.length();
					// "%" y "#" indican el inicio y el final, respectivamente, de las imágenes
					habilidades = habilidades.substring(0, localizaciónImagen) + "%" + nombreImagen + "#"
							+ habilidades.substring(localizaciónImagen + longitudNombreImagen);
					carta.setHabilidades(habilidades);
				}
			}
		}
		theModel.addAttribute("listaCartas", cartas);
	}

	private void añadirElementos(Model theModel) {
		Cartas cartaBuscada = new Cartas();
		Combobox combobox = new Combobox();
		// Para poder pasar dos atributos en el formulario, estos se tienen que poner en
		// un objeto común
		theModel.addAttribute("combinacionCartaCombobox", new CartaAndCombobox(cartaBuscada, combobox));
	}

	private String devolverBusquedaOBusquedaFallida(Model theModel, String nombreCarta,
			ArrayList<Cartas> cartasRecuperadas) {
		if (cartasRecuperadas.isEmpty()) {
			return devolverBusquedaFallida(theModel, nombreCarta);
		} else {
			return devolverBusquedaConLista(theModel, cartasRecuperadas);
		}
	}

	private String devolverBusquedaFallida(Model theModel, String nombreCarta) {
		theModel.addAttribute("nombreCarta", nombreCarta);
		theModel.addAttribute("cartaBuscada", new Cartas());
		return "busquedaFallida";
	}
}
