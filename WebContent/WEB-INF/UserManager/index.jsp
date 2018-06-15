<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="login.css">
<title>GreenCar(c)</title>
</head>
<body>

<img alt="" src="logo.jpg">

	<form class="formlogin" action="login" method="POST">
		<br> 
		<br> 
		<fieldset>

			<br> <label> Adresse email <span class="requis">*</span></label>
			<input type="email" name="email" required="required" size=50
				placeholder="saisir l'adresse email" value="${forms['email']}">
			<span class="error">${errors['email']}</span> <br>
			<br> <label> Mot de passe <span class="requis">*</span></label>
			<input type="PASSWORD" required="required" name="pwd" size=50
				placeholder="saisir le mot de passe" value="${forms['pwd']}">
			<span class="error">${errors['pwd']}</span> <br>
			<br> <input class="bouton" type="SUBMIT" name="connexion"
				value="Connexion" checked>

		</fieldset>
		<br> <a href="Create">Création de compte</a>
	</form>
</body>
</html>

