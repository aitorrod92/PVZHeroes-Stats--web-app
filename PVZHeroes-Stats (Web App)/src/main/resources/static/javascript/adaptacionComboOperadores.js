$('#comboAtributos1').change(function() {
	var atributoSeleccionado = $(this).val();
	var campoOperadoresAModificar;
	var atributoAModificar;

	$('#comboOperadores1 > option').remove();
	
	var opciones;
	if (atributoSeleccionado == "Nombre" || atributoSeleccionado == "Clase") {
		/*campoOperadoresAModificar = "${combinacionCC1.comboboxOperadores.comboboxOperadoresString.valor}";
		atributoAModificar = "${combinacionCC1.carta.Nombre}"; */ //ESTO HA DE SER ADAPTADO
		opciones = ["Incluye", "Es exactamente", "No incluye"];
	} else {
		/*campoOperadoresAModificar = "${combinacionCC1.comboboxOperadores.comboboxOperadoresNumericos.valor}";
		atributoAModificar = "${combinacionCC1.carta.ValorNumerico}"; */ //ESTO HA DE SER ADAPTADO
		opciones = [">", ">=", "=", "<=", "<"];
	}
	/*$('#comboOperadores1').attr("th:field", campoOperadoresAModificar);
	$('#input1').attr("th:field", atributoAModificar);*/
	
	opciones.forEach(element => {
		jQuery('<option/>', {
			value: element,
			html: element
		}).appendTo('#comboOperadores1');
		
	/*location.reload();
	return false;*/
	});
})