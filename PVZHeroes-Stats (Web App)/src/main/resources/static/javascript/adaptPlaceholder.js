var opcionesNombre = "Peashooter, Conehead...";
var opcionesNumericas = "Valor igual o mayor a 0";
var opcionesClase = "Mega-Grow, Hearty...";
var opcionesTribus = "Pea, Professional...";
var opcionesAtributos = "Double-Strike, Armored...";
var opcionesHabilidades = "Word in the ability description";
var opcionesRareza = "Common, Legendary...";
var opcionesMazo = "Basic, Triasic...";
var opcionesTipo = "Plants or zombies";
var opciones;

$('.comboAtributos').change(function() {
	var atributoSeleccionado = $(this).val();
	switch (atributoSeleccionado) {
		case "Nombre":
			opciones = opcionesNombre;
			break;
		case "Ataque":
		case "Defensa":
		case "Coste":
		case "NumeroAtributos":
			opciones = opcionesNumericas;
			break;
		case "Clase":
			opciones = opcionesClase;
			break;
		case "Tribus":
			opciones = opcionesTribus;
			break;
		case "Atributos":
			opciones = opcionesAtributos;
			break;
		case "Habilidades":
			opciones = opcionesHabilidades;
			break;
		case "Rareza":
			opciones = opcionesRareza;
			break;
		case "Mazo":
			opciones = opcionesMazo;
			break;
		default:
			opciones = opcionesTipo;
			break;

	}
	var nombreCombo = $(this).attr('id');
	var longitudId = nombreCombo.length;
	var numeroCombo = nombreCombo.substring(longitudId-1);
	$("#comboAtributos" + numeroCombo).siblings('input').attr('placeholder', opciones);
})