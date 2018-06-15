/**
 * 
 */

function afficheOngletMap(onglet) {

	document.getElementById("divRoute").style.display  = "none"; 
	document.getElementById("divMap").style.display  = "block";

	document.getElementById("btnMap").className = "";
	document.getElementById("btnMap").className = "onglet actif";
	document.getElementById("btnRoute").className = "";
	document.getElementById("btnRoute").className = "onglet";
		
	
	
}

function afficheOngletRoute() {

	document.getElementById("divRoute").style.display  = "block"; 
	document.getElementById("divMap").style.display  = "none";

	document.getElementById("btnRoute").className = "";
	document.getElementById("btnRoute").className = "onglet actif";
	document.getElementById("btnMap").className = "";
	document.getElementById("btnMap").className = "onglet";
		
	
	
}
