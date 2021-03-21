$('#comboAtributos1').change(function() {
	var atributoSeleccionado = $(this).val();
	adaptarCombo(1, atributoSeleccionado);
})

$('#comboAtributos2').change(function() {
	var atributoSeleccionado = $(this).val();
	adaptarCombo(2, atributoSeleccionado);
})

$('#comboAtributos3').change(function() {
	var atributoSeleccionado = $(this).val();
	adaptarCombo(3, atributoSeleccionado);
})

function adaptarCombo(numeroCombo, atributoSeleccionado) {
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