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
	ArrayList<Cartas> cartasRecuperadas = new ArrayList();

	@Autowired
	private CardsService cardsService;

	@GetMapping("/index")
	public String mostrarPaginaInicial(Model theModel) {
		theModel.addAttribute("listaPosiblesImagenes", listaPosiblesImagenes);
		añadirElementos(theModel);
		cartasRecuperadas = cardsService.findAll();
		return devolverBusquedaOBusquedaFallida(theModel, "", cartasRecuperadas);
	}

	@RequestMapping("/busqueda")
	public String mostrarPaginaBusqueda(@ModelAttribute("combinacionCC1") CartaAndCombobox cartaAndCombobox,
			@ModelAttribute("combinacionCC2") CartaAndCombobox cartaAndCombobox2,
			@ModelAttribute("combinacionCartaCombobox3") CartaAndCombobox cartaAndCombobox3, Model theModel) {

		// INCLUIR EL PRIMER COMBO
		añadirElementos(theModel);

		String atributo = cartaAndCombobox.getComboboxA().getValor();
		String valor = cartaAndCombobox.getCarta().getValor();
		String operador = cartaAndCombobox.getComboboxOperadores().getValor();

		switch (atributo) {
		case "Ataque":
		case "Defensa":
		case "Coste":
		case "NumeroAtributos":
			return buscarCartas(theModel, valor, operador, atributo, "Numero");
		default:
			return buscarCartas(theModel, valor, operador, atributo, "String");
		}
	}

	private String buscarCartas(Model theModel, String valor, String operador, String atributo, String tipo) {
		try {
			if (tipo.equals("String")) {
				{
					valor = valor.trim();
					if (valor.toString().equals("")) {
						cartasRecuperadas = cardsService.findAll();
					} else {
						cartasRecuperadas = cardsService.findByPattern(valor, operador, atributo);
					}
					return devolverBusquedaOBusquedaFallida(theModel, valor, cartasRecuperadas);
				}
			} else {
				int valorNumerico = 0;
				try {
					valorNumerico = Integer.valueOf(valor);
				} catch (NumberFormatException e) {
				}
				cartasRecuperadas = cardsService.findByValue(valorNumerico, operador, atributo);
				return devolverBusquedaOBusquedaFallida(theModel, valor, cartasRecuperadas);
			}
		} catch (NoSuchElementException ex) {
			return devolverBusquedaFallida(theModel, valor.toString());
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

	private String devolverBusquedaConLista(Model theModel, ArrayList<Cartas> listaCartas) {
		añadirImagenesHabilidades(listaCartas);
		theModel.addAttribute("listaCartas", listaCartas);
		return "index";
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
