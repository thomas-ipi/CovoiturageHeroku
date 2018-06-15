var map, marker, marker2;
var depart, arrivee, ptCheck;
var listeetapes = new Array;
var listecoord;

var directionsService = new google.maps.DirectionsService();
var geocoder;

/* initialise google MAP V3 */
function init() {
	/* gestion des routes */
	directionsDisplay = new google.maps.DirectionsRenderer();
	var bl = new google.maps.LatLng(43.545368, 1.5134387);
	/* option par dÈfaut de la carte */
	var myOptions = {
		zoom : 11,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		center : bl
	}
	/* creation de la map */
	map = new google.maps.Map(document.getElementById("divMap"), myOptions);
	directionsDisplay.setMap(map);
	directionsDisplay.setPanel(document.getElementById("divRoute"));

	initialize();
	afficherTrajet();
	afficherMarker();

}

function trouveRoute_bon() {
	/* test si les variables sont bien initialis√©s */
	if (depart && arrivee) {
		var request = {
			origin : depart,
			destination : arrivee,
			travelMode : google.maps.DirectionsTravelMode["DRIVING"]
		};

		/* appel √† l'API pour tracer l'itin√©raire */
		directionsService
				.route(
						request,
						function(response, status) {
							if (status == google.maps.DirectionsStatus.OK) {
								directionsDisplay.setDirections(response);
								for (var int = 0; int < response.routes[0].overview_path.length; int++) {
									listeetapes
											.push(response.routes[0].overview_path[int]);
									listecoord = listeetapes
											+ response.routes[0].overview_path[int];
									document.getElementById('coordlist').value += response.routes[0].overview_path[int];
								}
							}
						});
	}

}

function trouveRoute(etape) {
	/* test si les variables sont bien initialis√©s */
	if (depart && arrivee) {


		// Si on vient d'une case ‡ cocher
		if (etape == true) {

			var locations = document.getElementsByClassName("userstrouves");
			checkboxes = document.getElementsByName("check");

			var wayPoints = [];
			
			for (var i = 0; i < checkboxes.length; i++) {
				var checkbox = checkboxes[i];

				if (checkbox.checked) {
					var elt = locations[i];
					var latlong = elt.dataset.latlong;

					wayPoints.push(
							{
								location:latlong, 
								stopover: false
							});
				}

			}
			var request = {
					origin : depart,
					destination : arrivee,
					waypoints:wayPoints,
					optimizeWaypoints: true,
					travelMode : google.maps.DirectionsTravelMode["DRIVING"]
				};

			
		} else {
			
			var request = {
					origin : depart,
					destination : arrivee,
					travelMode : google.maps.DirectionsTravelMode["DRIVING"]
				};
			
		}

		
		
		
		/* appel √† l'API pour tracer l'itin√©raire */
		directionsService
				.route(
						request,
						function(response, status) {
							if (status == google.maps.DirectionsStatus.OK) {
								
								directionsDisplay.setDirections(response);
								
								for (var int = 0; int < response.routes[0].overview_path.length; int++) {
									listeetapes.push(response.routes[0].overview_path[int]);
									listecoord = listeetapes + response.routes[0].overview_path[int];
									document.getElementById('coordlist').value += response.routes[0].overview_path[int];
								}
							}
						});
	}

}

function afficherTrajet() {
	if (geocoder) {
		geocoder.geocode({
			'address' : document.getElementById("depart").value
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				/* ajoute un marqueur √† l'adresse choisie */
				map.setCenter(results[0].geometry.location);
				if (marker) {
					marker.setMap(null);
				}
				marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location
				});
				/* on remplace l'adresse par celle fournie du geocoder */
				depart = results[0].formatted_address;
			}
		});

		geocoder.geocode({
			'address' : "231 rue pierre et marie curie 31676 Labege"
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				/* ajoute un marqueur √† l'adresse choisie */
				if (marker2) {
					marker2.setMap(null);
				}

				marker2 = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location

				});
				/* on remplace l'adresse par celle fournie du geocoder */
				arrivee = results[0].formatted_address;
			}

			trouveRoute();
		});

	}

}

function initialize() {
	/* Instanciation du geocoder */
	geocoder = new google.maps.Geocoder();

}

/* Fonction de gÈocodage dÈclenchÈe en cliquant surle bouton "Geocoder" */
function geoCoderAddress() {
	var address = document.getElementById("adresse1").value + " "
			+ document.getElementById("codepostal").value + " "
			+ document.getElementById("ville").value;
	/* Appel au service de geocodage avec l'adresse en paramËtre */
	geocoder
			.geocode(
					{
						'address' : address
					},
					function(results, status) {
						/* Si l'adresse a pu Ítre gÈolocalisÈe */
						if (status == google.maps.GeocoderStatus.OK) {
							/* RÈcupÈration de sa latitude et de sa longitude */
							document.getElementById('latlong').value = results[0].geometry.location
									.lat()
									+ "," + results[0].geometry.location.lng();
						} else {
							alert("Le geocodage n\'a pu etre effectue pour la raison suivante: "
									+ status);
						}
					});
}

function afficherMarker() {

	var locations = document.getElementsByClassName("userstrouves");
	var infowindow = new google.maps.InfoWindow();

	var marker, i;

	for (i = 0; i < locations.length; i++) {

		var elt = locations[i];
		var latlong = elt.dataset.latlong;
		latlong = latlong.replace(" ", "");
		var t_latlong = latlong.split(",");

		marker = new google.maps.Marker({
			position : new google.maps.LatLng(t_latlong[0], t_latlong[1]),
			map : map,
			icon : 'http://maps.google.com/mapfiles/marker_yellow.png',
			title : elt.dataset.marker
		});

		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infowindow.setContent(elt.dataset.nom);
				infowindow.open(map, marker);
			}
		})(marker, i));
	}
}
