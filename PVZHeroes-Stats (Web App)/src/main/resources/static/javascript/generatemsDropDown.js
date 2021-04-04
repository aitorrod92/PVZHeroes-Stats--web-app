	$(document).ready(function(e) {
		try {
			$("#comboClases").msDropDown();
			$("#comboAtributos").msDropDown();
			$("#comboTipos").msDropDown();
		} catch (e) {
			alert(e.message);
		}
	});