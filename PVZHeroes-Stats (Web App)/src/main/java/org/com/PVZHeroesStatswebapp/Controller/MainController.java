package org.com.PVZHeroesStatswebapp.Controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.com.PVZHeroesStatswebapp.Entities.CartaAndCombobox;
import org.com.PVZHeroesStatswebapp.Entities.Cartas;
import org.com.PVZHeroesStatswebapp.Entities.ComboNumeroFiltros;
import org.com.PVZHeroesStatswebapp.Entities.ComboboxOperadores;
import org.com.PVZHeroesStatswebapp.Entities.Formulario;
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

	String atributo;
	String operador;
	String valor;

	@Autowired
	private CardsService cardsService;

	@GetMapping("/index")
	public String mostrarPaginaInicial(Model theModel) {
		theModel.addAttribute("listaPosiblesImagenes", listaPosiblesImagenes);
		añadirElementos(theModel);
		cartasRecuperadas = cardsService.findAll();
		return "index";
	}

	@RequestMapping("/busqueda")
	public String mostrarPaginaBusqueda(@ModelAttribute("formulario") Formulario formulario, Model theModel) {
		añadirElementos(theModel);
		CartaAndCombobox CC1 = formulario.getCC1();
		CartaAndCombobox CC2 = formulario.getCC2();
		ArrayList<String> stringsFiltros = new ArrayList();

		obtenerValoresFiltro(CC1);
		String atributo1 = CC1.getComboboxA().getValor();
		String valor1 = valor;
		stringsFiltros.add(atributo1);
		stringsFiltros.add(valor1);
		ArrayList<Cartas> lista1 = buscarEnFunciónDeAtributoSeleccionado(theModel, atributo1);

		obtenerValoresFiltro(CC2);
		String atributo2 = CC2.getComboboxA().getValor();
		String valor2 = valor;
		stringsFiltros.add(atributo2);
		stringsFiltros.add(valor2);
		ArrayList<Cartas> lista2 = buscarEnFunciónDeAtributoSeleccionado(theModel, atributo2);

		ArrayList<Cartas> listaCartasComunes = new ArrayList();
		for (Cartas carta : lista1) {
			if (lista2.contains(carta)) {
				listaCartasComunes.add(carta);
			}
		}

		if (!listaCartasComunes.isEmpty()) {
			añadirImagenesHabilidades(listaCartasComunes);
			theModel.addAttribute("listaCartas", listaCartasComunes);
			return "index";
		} else {
			return devolverBusquedaFallida(theModel, stringsFiltros);
		}
	}

	private void obtenerValoresFiltro(CartaAndCombobox cartaAndCombobox) {
		atributo = cartaAndCombobox.getComboboxA().getValor();
		operador = cartaAndCombobox.getComboboxOperadores().getValor();
		switch (atributo) {
		case "Mazo":
		case "Rareza":
			valor = cartaAndCombobox.getCarta().getValorCombo();
			break;
		case "Atributos":
			valor = cartaAndCombobox.getCarta().getValorComboAtributos();
			break;
		case "Clase":
			valor = cartaAndCombobox.getCarta().getValorComboClases();
			break;
		case "Tipo":
			valor = cartaAndCombobox.getCarta().getValorComboTipo();
			break;
		default:
			valor = cartaAndCombobox.getCarta().getValorInput();
			break;
		}
		System.out.println("Atributo: " + atributo + " operador: " + operador + " valor: " + valor);
	}

	private ArrayList<Cartas> buscarEnFunciónDeAtributoSeleccionado(Model theModel, String atributo) {
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

	private ArrayList<Cartas> buscarCartas(Model theModel, String valor, String operador, String atributo,
			String tipo) {
		try {
			if (tipo.equals("String")) {
				{
					valor = valor.trim();
					if (valor.toString().equals("")) {
						cartasRecuperadas = cardsService.findAll();
					} else {
						cartasRecuperadas = cardsService.findByPattern(valor, operador, atributo);
					}
				}
			} else {
				int valorNumerico = 0;
				try {
					valorNumerico = Integer.valueOf(valor);
				} catch (NumberFormatException e) {
				}
				cartasRecuperadas = cardsService.findByValue(valorNumerico, operador, atributo);
			}
			return cartasRecuperadas;
		} catch (NoSuchElementException ex) {
			return null;
		}

	}

	private void añadirElementos(Model theModel) {
		ComboNumeroFiltros comboNumeroFiltros = new ComboNumeroFiltros();
		theModel.addAttribute("comboNumeroFiltros", comboNumeroFiltros);

		ComboboxOperadores comboOperadores = new ComboboxOperadores();

		// Para poder pasar dos atributos en el formulario, estos se tienen que poner en
		// un objeto común
		theModel.addAttribute("formulario", new Formulario());
	}

	private String devolverBusquedaFallida(Model theModel, ArrayList<String> listaFiltros) {
		String tipoString;
		for (int i = 0; i < listaFiltros.size(); i++) {
			if (i % 2 == 0) {
				tipoString = "atributo";
			} else {
				tipoString = "valor";
			}
			theModel.addAttribute(tipoString + i, listaFiltros.get(i));
		}
		theModel.addAttribute("listaFiltros", listaFiltros);
		return "busquedaFallida";
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

	@GetMapping("/error")
	public String mostrarPaginaError() {
		return "error";
	}
}
