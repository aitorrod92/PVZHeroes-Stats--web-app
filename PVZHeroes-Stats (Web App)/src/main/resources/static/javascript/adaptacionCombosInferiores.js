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
			mostrarInputYOcultarCombo(false, numeroCombo, atributoSeleccionado);
			if (atributoSeleccionado == "Mazo" || atributoSeleccionado == "Rareza") {
				asignarValoresCombo(atributoSeleccionado, numeroCombo);
			}
			break;
		default:
			mostrarInputYOcultarCombo(true, numeroCombo);
			break;
	}
}

function mostrarInputYOcultarCombo(valor, numeroCombo, filtro) {
	if (valor == true) {
		ActivarPrimeroYDesactivarElResto(
			$('#input' + numeroCombo),
			$('#comboOpcionesAtributo' + numeroCombo),
			$('#comboClases' + numeroCombo + '_msdd'),
			$('#comboAtrib' + numeroCombo + '_msdd'),
			$('#comboTipos' + numeroCombo + '_msdd'),
		);
	} else {
		$('#input' + numeroCombo).attr("style", "display:none");
		switch (filtro) {
			case "Clase":
				ActivarPrimeroYDesactivarElResto(
					$('#comboClases' + numeroCombo + '_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#comboTipos' + numeroCombo + '_msdd'),
					$('#input' + numeroCombo)
				);
				$('#comboClases' + numeroCombo + '_msdd').addClass("mb-4 sb-4");
				break;
			case "Atributos":
				ActivarPrimeroYDesactivarElResto(
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboClases' + numeroCombo + '_msdd'),
					$('#comboTipos' + numeroCombo + '_msdd'),
					$('#input' + numeroCombo),
				);
				$('#comboAtrib' + numeroCombo + '_msdd').addClass("mb-4 sb-4");
				break;
			case "Tipo":
				ActivarPrimeroYDesactivarElResto(
					$('#comboTipos' + numeroCombo + '_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboClases' + numeroCombo + '_msdd'),
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#input' + numeroCombo),
				);
				$('#comboTipos' + numeroCombo + '_msdd').addClass("mb-4 sb-4");
				break;
			default:
				ActivarPrimeroYDesactivarElResto(
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#comboClases' + numeroCombo + '_msdd'),
					$('#comboTipos' + numeroCombo + '_msdd'),
					$('#input' + numeroCombo),
				);
				break;
		}
		$('#comboOpcionesAtributo' + numeroCombo + '> option').remove();
	}
}

function asignarValoresCombo(atributoSeleccionado, numeroCombo) {
	var opciones;
	var opcionesRareza = ["Common", "Basic", "Rare", "Super-rare", "Uncommon", "Legendary"];
	var opcionesMazo = ["Sin mazo", "Basic", "Premium", "Legendary", "Galactic", "Colossal", "Triassic"];

	switch (atributoSeleccionado) {
		case "Rareza":
			opciones = opcionesRareza;
			break;
		case "Mazo":
			opciones = opcionesMazo;
			break;
	}

	opciones.forEach(element => {
		jQuery('<option/>', {
			value: element,
			html: element
		}).appendTo('#comboOpcionesAtributo' + numeroCombo);
	});
}

function ActivarPrimeroYDesactivarElResto(ElementoActivado, ElementoDesactivado1,
	ElementoDesactivado2, ElementoDesactivado3, ElementoDesactivado4) {
	ElementoActivado.attr("style", "width: 200px; display: block");
	ElementoDesactivado1.attr("style", "display:none");
	ElementoDesactivado2.attr("style", "display:none");
	ElementoDesactivado3.attr("style", "display:none");
	ElementoDesactivado4.attr("style", "display:none");
}






