$('.comboAtributos').change(function() {
	var atributoSeleccionado = $(this).val();
	var nombreCombo = $(this).attr('id');
	var longitudId = nombreCombo.length;
	var numeroCombo = nombreCombo.substring(longitudId - 1);
	adaptarComboOperadores(numeroCombo, atributoSeleccionado);
	elegirInputOCombo(numeroCombo, atributoSeleccionado);
})

function adaptarComboOperadores(numeroCombo, atributoSeleccionado) {
	var opciones;

	$('#comboOperadores' + numeroCombo + '> option').remove();

	if (atributoSeleccionado == "Ataque" || atributoSeleccionado == "Defensa"
		|| atributoSeleccionado == "Coste" || atributoSeleccionado == "NumeroAtributos") {
		opciones = [">", ">=", "=", "<=", "<"];
	} else {
		opciones = ["Incluye", "Es exactamente", "No incluye"];
	}
	opciones.forEach(element => {
		jQuery('<option/>', {
			value: element,
			html: element
		}).appendTo('#comboOperadores' + numeroCombo);
	});
};

function elegirInputOCombo(numeroCombo, atributoSeleccionado) {
	switch (atributoSeleccionado) {
		case "Clase":
		case "Mazo":
		case "Rareza":
		case "Tipo":
		case "Atributos":
			mostrarInputYOcultarCombo(false, numeroCombo);
			asignarValoresCombo(atributoSeleccionado, numeroCombo);
			break;
		default:
			mostrarInputYOcultarCombo(true, numeroCombo);
			break;
	}
}

function mostrarInputYOcultarCombo(valor, numeroCombo) {
	if (valor == true) {
		$('#input' + numeroCombo).attr("style", "display:block");
		$('#comboOpcionesAtributo' + numeroCombo).attr("style", "display:none");
	} else {
		$('#input' + numeroCombo).attr("style", "display:none");
		$('#comboOpcionesAtributo' + numeroCombo).attr("style", "display:block");
		$('#comboOpcionesAtributo' + numeroCombo + '> option').remove();
	}
}

function asignarValoresCombo(atributoSeleccionado, numeroCombo) {
	var opciones;
	var opcionesRareza = ["Common", "Basic", "Rare", "Super-rare", "Uncommon", "Legendary"];
	var opcionesMazo = ["Sin mazo", "Basic", "Premium", "Legendary", "Galactic", "Colossal", "Triassic"];
	var opcionesClase = ["Beastly", "Brainy", "Crazy", "Guardian", "Hearty", "Kabloom", "Mega-Grow", "Smarty", "Sneaky", "Solar"];
	var opcionesTipo = ["Plants", "Zombies"];
	var opcionesAtributos = ["Double Strike", "Splash Damage", "Team-Up", "Deadly", "Frenzy", "Gravestone", "Overshoot", "Amphibious", "Anti-Hero", "Armored", "Hunt", "Bullseye", "Strikethrough", "Untrickable", "Ninguno"];

	switch (atributoSeleccionado) {
		case "Rareza":
			opciones = opcionesRareza;
			break;
		case "Mazo":
			opciones = opcionesMazo;
			break;
		case "Clase":
			opciones = opcionesClase;
			break;
		case "Tipo":
			opciones = opcionesTipo;
			break;
		case "Atributos":
			opciones = opcionesAtributos;
			break;
	}

	opciones.forEach(element => {
		jQuery('<option/>', {
			value: element,
			html: element
		}).appendTo('#comboOpcionesAtributo' + numeroCombo);
	});
}



