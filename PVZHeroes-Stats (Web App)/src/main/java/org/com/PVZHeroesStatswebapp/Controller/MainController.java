package org.com.PVZHeroesStatswebapp.Controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.com.PVZHeroesStatswebapp.Entities.CartaAndCombobox;
import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.com.PVZHeroesStatswebapp.Entities.ComboNumeroFiltros;
import org.com.PVZHeroesStatswebapp.Entities.ComboboxAtributo;
import org.com.PVZHeroesStatswebapp.Entities.ComboboxNumerico;
import org.com.PVZHeroesStatswebapp.Entities.ComboboxStrings;
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
	public String mostrarPaginaBusqueda(@ModelAttribute("combinaciónCartaCombobox") CartaAndCombobox cartaAndCombobox,
			@ModelAttribute("combinaciónCartaCombobox2") CartaAndCombobox cartaAndCombobox2,
			@ModelAttribute("combinacionCartaCombobox3") CartaAndCombobox cartaAndCombobox3, Model theModel) {
		/*
		 * String valorCombo = cartaAndCombobox.getCombobox().getValor(); String
		 * nombreCarta = cartaAndCombobox.getCarta().getNombre().trim();
		 * añadirElementos(theModel); ArrayList<Cartas> cartasRecuperadas = new
		 * ArrayList(); try { if (nombreCarta.equals("")) { cartasRecuperadas =
		 * cardsService.findAll(); return devolverBusquedaConLista(theModel,
		 * cartasRecuperadas); } else { switch (valorCombo) { case "==": case "": return
		 * devolverBusquedaMonoResultado(theModel, nombreCarta); case "LIKE":
		 * cartasRecuperadas = cardsService.findByPatternId(nombreCarta, true); return
		 * devolverBusquedaOBusquedaFallida(theModel, nombreCarta, cartasRecuperadas);
		 * case "NOT LIKE": cartasRecuperadas =
		 * cardsService.findByPatternId(nombreCarta, false); return
		 * devolverBusquedaOBusquedaFallida(theModel, nombreCarta, cartasRecuperadas);
		 * default: return null; } } } catch (NoSuchElementException ex) { return
		 * devolverBusquedaFallida(theModel, nombreCarta); }
		 */

		String operador = cartaAndCombobox2.getComboboxN().getValor();
		String atributo = cartaAndCombobox3.getComboboxA().getValor();
		int valor = cartaAndCombobox3.getCarta().getValorNumerico();
		System.out.println("valor " + valor);
		
		añadirElementos(theModel);
		ArrayList<Cartas> cartasRecuperadas = new ArrayList();
		try {
			if (valor == 0) {
				cartasRecuperadas = cardsService.findAll();
				return devolverBusquedaConLista(theModel, cartasRecuperadas);
			} else {
				cartasRecuperadas = cardsService.findByValue(valor, operador, atributo);
				return devolverBusquedaOBusquedaFallida(theModel, String.valueOf(valor), cartasRecuperadas);
			}
		} catch (NoSuchElementException ex) {
			return devolverBusquedaFallida(theModel, String.valueOf(valor));
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
		Cartas cartaBuscada = new Cartas();
		ComboboxStrings combobox = new ComboboxStrings();
		ComboboxNumerico comboboxN = new ComboboxNumerico();
		ComboboxAtributo comboboxA = new ComboboxAtributo();
		ComboNumeroFiltros numeroFiltros = new ComboNumeroFiltros();

		// Para poder pasar dos atributos en el formulario, estos se tienen que poner en
		// un objeto común
		theModel.addAttribute("combinacionCartaCombobox", new CartaAndCombobox(cartaBuscada, combobox));
		theModel.addAttribute("combinacionCartaCombobox2", new CartaAndCombobox(cartaBuscada, comboboxN));
		theModel.addAttribute("combinacionCartaCombobox3", new CartaAndCombobox(cartaBuscada, comboboxA));
		theModel.addAttribute("comboNumeroFiltros", numeroFiltros);
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
