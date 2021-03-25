var i = 1;

$(document).ready(function() {
	movingLoop("derecha");
	$("#berry").click(function() {
		$('#berry').attr('id', 'explosion');

		$('#explosion').attr('src', '/images/decoration/explosion.gif');
		$('#explosion').attr('width', '200');


		setTimeout(removeExplosion, 1000);
	});
});

function movingLoop(direccion) {
	setTimeout(function() { move(direccion) }, 2000)
}

function move(direccion) {
	var espacioALaIzquierda;
	if (direccion.localeCompare("izquierda")) {
		$('#berry').attr('class', 'berry');
		$('#berry').animate({ left: '1%' });
	} else {
		$('#berry').attr('class', 'rotatedBerry');
		$('#berry').animate({ left: '10%' });
	}

	i++;
	if (i % 2 == 0) {
		movingLoop("izquierda");
	} else {
		movingLoop("derecha");
	}
}


function removeExplosion() {
	$('#explosion').remove();
}



