$(document).ready(function(e) {
	try {
		$("#comboClases").msDropDown();
		$("#comboAtrib1").msDropDown();
		$("#comboTipos").msDropDown();
		CrearListeners();
	} catch (e) {
		alert(e.message);
	}
});


function CrearListeners() {
	for (let i = 0; i < 2; i++) {
		console.log("comboAtrib" + i + "_child");
		$("#comboAtrib" + i).click(function() {
			$("#comboAtrib" + i + "_child").attr("style", "");
			console.log("ojo: comboAtrib" + i + "_child");
		});

	}
}