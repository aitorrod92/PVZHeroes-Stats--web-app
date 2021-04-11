$(document).ready(function(e) {
	try {
		CrearCombos();
		$("#comboClases").msDropDown();
		$("#comboTipos").msDropDown();
		CrearListeners();
	} catch (e) {
		alert(e.message);
	}
});

function CrearCombos() {
	for (let i = 0; i < 6; i++) {
		$("#comboAtrib" + i).msDropDown();
	}
}


function CrearListeners() {
	for (let i = 0; i < 6; i++) {
		$("#comboAtrib" + i).click(function() {
			$("#comboAtrib" + i + "_child").attr("style", "");
		});

	}
}