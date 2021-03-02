$('#comboNumeroFiltros').change(function () {
	var numeroFiltros = $(this).val();
	console.log(numeroFiltros);

	$('.filtroAdicional').each(function (i, obj) {
		if (i < numeroFiltros-1) {
			obj.hidden = false;
		} else {
			obj.hidden = true;
		}
	});

	$('.tituloAdicional').each(function (i, obj) {
		if (i < numeroFiltros-1) {
			obj.hidden = false;
		} else {
			obj.hidden = true;
		}
	});
})