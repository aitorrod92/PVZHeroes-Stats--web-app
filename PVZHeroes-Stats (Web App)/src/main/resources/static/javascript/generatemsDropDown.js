var numeroFiltros = 6;

$(document).ready(function(e) {
	try {
		CrearCombos();
		CrearListeners();
	} catch (e) {
		alert(e.message);
	}
});

function CrearCombos() {
	for (let i = 0; i < numeroFiltros + 1; i++) {
		$("#comboAtrib" + i).msDropDown();
		$("#comboTipos" + i).msDropDown();
		$("#comboClases" + i).msDropDown();
	}
}

// Listeners para reiniciar los combos y que funcionen
function CrearListeners() {
	for (let i = 0; i < 6; i++) {
		$("#comboAtrib" + i).click(function() {
			$("#comboAtrib" + i + "_child").attr("style", "");
		});
		$("#comboTipos" + i).click(function() {
			$("#comboTipos" + i + "_child").attr("style", "");
		});

		$("#comboClases" + i).click(function() {
			$("#comboClases" + i + "_child").attr("style", "");
		});

	}
}