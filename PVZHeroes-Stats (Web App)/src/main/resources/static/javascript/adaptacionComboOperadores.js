$('#comboAtributos1').change(function() {
	var atributoSeleccionado = $(this).val();
	var opciones;

	$('#comboOperadores1 > option').remove();
	
	if (atributoSeleccionado == "Nombre" || atributoSeleccionado == "Clase") {
		opciones = ["Incluye", "Es exactamente", "No incluye"];
	} else {
		opciones = [">", ">=", "=", "<=", "<"];
	}	
	opciones.forEach(element => {
		jQuery('<option/>', {
			value: element,
			html: element
		}).appendTo('#comboOperadores1');
	});
})