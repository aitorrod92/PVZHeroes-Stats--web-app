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
	if (atributoSeleccionado == "Clase" || atributoSeleccionado == "Tribus"
		|| atributoSeleccionado == "Mazo" || atributoSeleccionado == "Rareza"
		|| atributoSeleccionado == "Tipo" || atributoSeleccionado == "Atributos") {
		$('#input' + numeroCombo).attr("style", "display:none");
		$('#comboOpcionesAtributo' + numeroCombo).attr("style", "display:block");
		$('#comboOpcionesAtributo' + numeroCombo + '> option').remove();
	} else {
		$('#input' + numeroCombo).attr("style", "display:block");
		$('#comboOpcionesAtributo' + numeroCombo).attr("style", "display:none");
	}


	if (atributoSeleccionado == "Rareza") {
		var opcionesClase = ["Common", "Basic", "Rare", "Super-rare", "Uncommon", "Legendary"];
		opcionesClase.forEach(element => {
			jQuery('<option/>', {
				value: element,
				html: element
			}).appendTo('#comboOpcionesAtributo' + numeroCombo);
		});
	};
}

