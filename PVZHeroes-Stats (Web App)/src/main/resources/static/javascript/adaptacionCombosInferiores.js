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
			$('#comboAtributos_msdd'),
			$('#comboTipos_msdd'),
		);
	} else {
		$('#input' + numeroCombo).attr("style", "display:none");
		switch (filtro) {
			case "Clase":
				ActivarPrimeroYDesactivarElResto(
					$('#comboClases_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboAtributos_msdd'),
					$('#comboTipos_msdd'),
					$('#input' + numeroCombo)
				);
				$('#comboClases_msdd').addClass("mb-4 sb-4");
				DesactivarSetterCombosImagenes($('#comboAtributos'), $('#comboTipos'));
				break;
			case "Atributos":
				ActivarPrimeroYDesactivarElResto(
					$('#comboAtributos_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboClases_msdd'),
					$('#comboTipos_msdd'),
					$('#input' + numeroCombo),
				);
				$('#comboAtributos_msdd').addClass("mb-4 sb-4");
				DesactivarSetterCombosImagenes($('#comboClases'), $('#comboTipos'));
				break;
			case "Tipo":
				ActivarPrimeroYDesactivarElResto(
					$('#comboTipos_msdd'),
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboClases_msdd'),
					$('#comboAtributos_msdd'),
					$('#input' + numeroCombo),
				);
				$('#comboTipos_msdd').addClass("mb-4 sb-4");
				DesactivarSetterCombosImagenes($('#comboClases'), $('#comboAtributos'));
				break;
			default:
				ActivarPrimeroYDesactivarElResto(
					$('#comboOpcionesAtributo' + numeroCombo),
					$('#comboAtributos_msdd'),
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

$('#comboAtributos').click(function() {
	$('#comboAtributos_child').attr("style", "");
});

$('#comboTipos').click(function() {
	$('#comboTipos_child').attr("style", "");
});

// NO SE PUEDE CAMBIAR ESE CAMPO UNA VEZ YA ESTÁ SELECCIONADO, 
//PROBABLEMENTE HACER UN COMPONENTE COMBO DIFERENTE POR CADA
// O HACER ALGO EN EL SETTER QUE IDENTIFIQUE EL CAMPO QUE ESTÁ ACTIVO
function DesactivarSetterCombosImagenes(ComboADesactivar1, ComboADesactivar2){
	ComboADesactivar1.attr("th:field", "");
	ComboADesactivar2.attr("th:field", "");
}









