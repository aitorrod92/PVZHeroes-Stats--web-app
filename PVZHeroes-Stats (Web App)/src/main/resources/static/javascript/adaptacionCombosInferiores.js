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
			$('#comboClases_msdd'),
			$('#comboAtrib' + numeroCombo + '_msdd'),
			$('#comboTipos_msdd'),
		);
	} else {
		$('#input' + numeroCombo).attr("style", "display:none");
		switch (filtro) {
			case "Clase":
				ActivarPrimeroYDesactivarElResto(
					$('#comboClases_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#comboTipos_msdd'),
					$('#input' + numeroCombo)
				);
				$('#comboClases_msdd').addClass("mb-4 sb-4");
				break;
			case "Atributos":
				ActivarPrimeroYDesactivarElResto(
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboClases_msdd'),
					$('#comboTipos_msdd'),
					$('#input' + numeroCombo),
				);
				$('#comboAtributos_msdd').addClass("mb-4 sb-4");
				break;
			case "Tipo":
				ActivarPrimeroYDesactivarElResto(
					$('#comboTipos_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboClases_msdd'),
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#input' + numeroCombo),
				);
				$('#comboTipos_msdd').addClass("mb-4 sb-4");
				break;
			default:
				ActivarPrimeroYDesactivarElResto(
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboAtrib' + numeroCombo + '_msdd'),
					$('#comboClases_msdd'),
					$('#comboTipos_msdd'),
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


// Reinicia el combo
$('#comboClases').click(function() {
	$('#comboClases_child').attr("style", "");
});



$('#comboTipos').click(function() {
	$('#comboTipos_child').attr("style", "");
});









