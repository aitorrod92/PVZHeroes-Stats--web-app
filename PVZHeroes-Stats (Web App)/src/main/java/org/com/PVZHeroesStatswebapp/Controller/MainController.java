package org.com.PVZHeroesStatswebapp.Controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.com.PVZHeroesStatswebapp.Entities.CartaAndCombobox;
import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.com.PVZHeroesStatswebapp.Entities.ComboNumeroFiltros;
import org.com.PVZHeroesStatswebapp.Entities.ComboboxOperadores;
import org.com.PVZHeroesStatswebapp.Service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	String[] listaPosiblesImagenes = { "strength", "health", "suns", "brains", "freeze", "bullseye", "double strike",
			"strikethrough", "deadly", "untrickable", "overshoot", "frenzy" };

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
	public String mostrarPaginaBusqueda(@ModelAttribute("combinacionCC1") CartaAndCombobox cartaAndCombobox,
			@ModelAttribute("combinacionCC2") CartaAndCombobox cartaAndCombobox2,
			@ModelAttribute("combinacionCartaCombobox3") CartaAndCombobox cartaAndCombobox3, Model theModel) {

		// INCLUIR EL PRIMER COMBO
		añadirElementos(theModel);
		@SuppressWarnings("unchecked")
		ArrayList<Cartas> cartasRecuperadas = new ArrayList();

		String atributo = cartaAndCombobox.getComboboxA().getValor();
		String valor = cartaAndCombobox.getCarta().getValor();
		String operador = null;

		switch (atributo) {
		case "Nombre":
		case "Clase":
			valor = valor.trim();
			operador = cartaAndCombobox.getComboboxOperadores().getValor();
			try {
				if (valor.toString().equals("")) {
					cartasRecuperadas = cardsService.findAll();
					return devolverBusquedaConLista(theModel, cartasRecuperadas);
				} else {
					switch (operador) {
					case "==":
					case "":
						return devolverBusquedaMonoResultado(theModel, valor.toString());
					case "LIKE":
						cartasRecuperadas = cardsService.findByPatternId(valor.toString(), true);
						return devolverBusquedaOBusquedaFallida(theModel, valor.toString(), cartasRecuperadas);
					case "NOT LIKE":
						cartasRecuperadas = cardsService.findByPatternId(valor.toString(), false);
						return devolverBusquedaOBusquedaFallida(theModel, valor.toString(), cartasRecuperadas);
					default:
						return null;
					}
				}
			} catch (NoSuchElementException ex) {
				return devolverBusquedaFallida(theModel, valor.toString());
			}
		case "Ataque":
		case "Defensa":
		case "Coste":
		default:
			operador = cartaAndCombobox.getComboboxOperadores().getValor();
			int ValorNumerico = 0;
			try {
				ValorNumerico = Integer.valueOf(valor);
			} catch (NumberFormatException e) {	}

			try {
				cartasRecuperadas = cardsService.findByValue(ValorNumerico, operador, atributo);
				return devolverBusquedaOBusquedaFallida(theModel, valor, cartasRecuperadas);
			} catch (NoSuchElementException ex) {
				return devolverBusquedaFallida(theModel, String.valueOf(valor));
			}
		}

	}

	private String devolverBusquedaMonoResultado(Model theModel, String nombreCarta) {
		Cartas cartaRecuperada = cardsService.findById(nombreCarta);
		ArrayList<Cartas> carta = new ArrayList<Cartas>();
		carta.add(cartaRecuperada);
		añadirImagenesHabilidades(carta);
		theModel.addAttribute("cartaBuscada", carta.get(0));
		return "busqueda";
	}

	private String devolverBusquedaConLista(Model theModel, ArrayList<Cartas> listaCartas) {
		añadirImagenesHabilidades(listaCartas);
		theModel.addAttribute("listaCartas", listaCartas);
		return "index";
	}

	private void añadirListadoCompletoAlModelo(Model theModel) {
		ArrayList<Cartas> cartas = cardsService.findAll();
		añadirImagenesHabilidades(cartas);
		theModel.addAttribute("listaCartas", cartas);
	}

	private void añadirImagenesHabilidades(ArrayList<Cartas> cartas) {
		for (Cartas carta : cartas) {
			String habilidades = carta.getHabilidades();
			for (String nombreImagen : listaPosiblesImagenes) {
				if (habilidades.contains(nombreImagen)) {
					int Repeticiones = calcularNumeroVecesContieneImagen(habilidades, nombreImagen);
					habilidades = añadirMarcadoresImagenesAHabilidades(carta, habilidades, nombreImagen, Repeticiones);
				}
			}
		}
	}

	private void añadirElementos(Model theModel) {
		ComboNumeroFiltros comboNumeroFiltros = new ComboNumeroFiltros();
		theModel.addAttribute("comboNumeroFiltros", comboNumeroFiltros);

		ComboboxOperadores comboOperadores = new ComboboxOperadores();

		// Para poder pasar dos atributos en el formulario, estos se tienen que poner en
		// un objeto común
		theModel.addAttribute("combinacionCC1", new CartaAndCombobox());
		theModel.addAttribute("combinacionCC2", new CartaAndCombobox());
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

	private int calcularNumeroVecesContieneImagen(String habilidades, String imagen) {
		int ultimoIndice = 0;
		int repeticiones = 0;

		while (ultimoIndice != -1) {

			ultimoIndice = habilidades.indexOf(imagen, ultimoIndice);

			if (ultimoIndice != -1) {
				repeticiones++;
				ultimoIndice += imagen.length();
			}
		}
		return repeticiones;
	}

	private String añadirMarcadoresImagenesAHabilidades(Cartas carta, String habilidades, String nombreImagen,
			int Repeticiones) {
		for (int i = 0; i < Repeticiones; i++) {
			int localizaciónImagen;
			if (i == 0) {
				localizaciónImagen = habilidades.indexOf(nombreImagen);
			} else {
				localizaciónImagen = habilidades.lastIndexOf(nombreImagen);
			}
			int longitudNombreImagen = nombreImagen.length();
			// "%" y "#" indican el inicio y el final, respectivamente, de las imágenes
			habilidades = habilidades.substring(0, localizaciónImagen) + "%" + nombreImagen + "#"
					+ habilidades.substring(localizaciónImagen + longitudNombreImagen);
			carta.setHabilidades(habilidades);
			/*
			 * if (i==1) { System.out.println(carta.getHabilidades());}
			 */
		}
		return habilidades;
	}
}
