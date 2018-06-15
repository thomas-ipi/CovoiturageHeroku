<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="Style.css">
<!--importation de l'API google MAP Version 3-->
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="googleMaps.js" language="javascript"></script>
<script type="text/javascript" src="js/display.js" language="javascript"></script>

</head>
<body onload="init();">


	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div style="float: bottom;">
		<%@ include file="../UserManager/entete.html"%>
	</div>

	<div>
		<div style="width: 49%; float: left;">
			<%@ include file="iti_recherche.jsp"%>
			<%@ include file="iti_tableau.jsp"%>
		</div>
	<div>
		
	</div>
	
		<div style="width: 49%; float: right;">
			<%@ include file="iti_map.jsp"%>
		</div>
	</div>

</body>
</html>


