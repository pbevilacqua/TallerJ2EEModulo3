/**
 * 
 */

function ocultarControl(id){
	var control = document.getElementById(id);
	if(control)
		control.style.display = "none";
}

function mostarControl(id,modo_display){
	var control = document.getElementById(id);
	if(control)
		control.style.display = modo_display;
}