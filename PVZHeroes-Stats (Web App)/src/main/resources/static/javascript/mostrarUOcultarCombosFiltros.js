$('#comboNumeroFiltros').change(function() {
	var numeroFiltros = $(this).val();

	$('.columnaFiltro').each(function(i, obj) {
		if (i < (numeroFiltros * 2 - 1)) {
			obj.hidden = false;
		} else {
			obj.hidden = true;
		}
	});

	$('.titulo').each(function(i, obj) {
		if (i < (numeroFiltros * 2 - 1)) {
			obj.hidden = false;
		} else {
			obj.hidden = true;
		}

	});



})