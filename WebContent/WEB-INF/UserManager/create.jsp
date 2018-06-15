<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Création d'un compte</title>
	<link rel="stylesheet" type="text/css" href="Style.css">
	<link rel="stylesheet" type="text/css" href="main.css">
	<script type="text/javascript" src="js/uploader.js" language="javascript"></script>
	<script type="text/javascript" src="googleMaps.js" language="javascript"></script>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
</head>

<body onload="initialize();" style="width: 1024px; margin: auto;">

	<%@include file="profilentete.html"%>

	<form name="form_register" title="Création d'un compte" action="Create"  method="POST" enctype="multipart/form-data" >

		<fieldset>
			<legend>Création d'un compte</legend>
			<div class="enblock">
				<div class="enligne">
					<fieldset class="agauche">
						<legend>Identité</legend>
						<input type="hidden" name="id" value="${forms['id']}"> 
						<input type="hidden" name="avatarFilename"  value="${forms['avatarFilename']}">
						<p>
							<label class="petitLabel">Profil <span class="requis">*</span></label>
							     <input type="radio" name="profil" value="0"  ${forms.profil=='0'?'checked':''}>Conducteur 
								 <input type="radio" name="profil" value="1"  ${forms.profil=='1'?'checked':''}>Passager 
								 <Br><span class="error">${erreurs['profil']}</span>
						</p><p>
							<label class="petitLabel">Nom <span class="requis">*</span></label> 
							<input type="text" name="nom" size=20 value="${forms['nom']}">
							<Br><span class="error">${erreurs['nom']}</span>
						</p><p>
							<label class="petitLabel">Prénom <span class="requis">*</span></label> 
							<input type="text" name="prenom" size=20 value="${forms['prenom']}">
							<Br><span class="error">${erreurs['prenom']}</span>
						</p><p>
							<label class="petitLabel">Genre</label>
							     <input type="radio" name="genre" value="feminin" ${forms.genre=='feminin'?'checked':''} >Femme 
							     <input type="radio" name="genre" value="masculin" ${forms.genre=='masculin'?'checked':''} >Homme 
							     <Br><span class="error">${erreurs['genre']}</span>
						</p><p>
							<label class="petitLabel">Fumeur</label>
							<input type="radio" name="fumeur" value="fumeur"  ${forms.fumeur=='fumeur'?'checked':''} >Oui 
							<input type="radio" name="fumeur" value="nonfumeur"  ${forms.fumeur=='nonfumeur'?'checked':''} >Non 
							<input type="radio" name="fumeur" value="indifferent"  ${forms.fumeur=='indifferent'?'checked':''}>Indifférent 
							<Br><span class="error">${erreurs['fumeur']}</span>
						</p>
					</fieldset>

					<fieldset class="agauche">
						<legend>Données de connexion</legend>
						<p>
							<label>Adresse email <span class="requis">*</span></label> 
							<input type="email" name="email" size=50 value="${forms['email']}">
							<Br><span class="error">${erreurs['email']}</span>
						</p><p>
							<label>Mot de passe <span class="requis">*</span></label> 
							<input type="password" name="pwd1" size=20 value="${forms['pwd1']}">
							<Br><span class="error">${erreurs['pwd1']}</span>
						</p><p>
							<label>Confirmation du mot de passe</label> 
							<input type="password" name="pwd2" size=20 value="${forms['pwd2']}">
							<Br><span class="error">${erreurs['pwd2']}</span>
						</p>
					</fieldset>
				</div>
				<div class="adroite">
					<fieldset >
						<legend>Photo</legend>
					<img id="imgAvatar"  alt="" src="${forms['avatar']}">
					<p text-align:center" >	
						<input type="file" name="avatar" id="avatar" style="width: 120px;"  onchange="uploadAvatar()"/>
					</p>
					</fieldset>
				</div>

			</div>

			<div style="display: inline-block; margin-bottom: 10px; margin-right: 10px">
				<fieldset style="display: inline; margin-bottom: 10px; margin-right: 10px; float: left;">
					<legend>Localisation</legend>
					<p>
						<label class="petitLabel">Adresse 1<span class="requis">*</span></label> 
						<input type="text" name="adresse1" id="adresse1" size=50 value="${forms['adresse1']}">
						<Br><span class="error">${erreurs['adresse1']}</span>
					</p><p>
						<label class="petitLabel">Adresse 2</label> 
						<input type="text" name="adresse2" id="adresse2" size=50 value="${forms['adresse2']}">
					</p><p>
						<label class="petitLabel">Code postal<span class="requis">*</span></label> 
						<input type="text" name="codepostal" id="codepostal" size=6 value="${forms['codepostal']}">
						<Br><span class="error">${erreurs['codepostal']}</span>
					</p><p>
						<label class="petitLabel">Ville<span class="requis">*</span></label> 
						<input type="text" name="ville" id="ville" size=30 value="${forms['ville']}" onchange="geoCoderAddress();">
						<Br><span class="error">${erreurs['ville']}</span>							
					</p><p>
						<input id="btnGeocoder" type="button" value="GeoCoder" class="bouton" onclick="geoCoderAddress();"> 
						<input type="text" name="latlong" id="latlong" size=30 value="${forms['latlong']}"> 
					</p>
				</fieldset>
				
				<fieldset style="display: inline; margin-bottom: 10px; margin-right: 10px; float: left;">
					<legend>Téléphones</legend>
					<p>
						<label class="petitLabel">Tél. fixe</label> <input type="text" name="telFixe" size=20
							value="${forms['telFixe']}">
					</p><p>
						<label class="petitLabel">Tél. portable</label> 
						<input type="text" name="telPortale" size=20 value="${forms['telPortale']}"> 
						<span class="error">${erreurs['telportable']}</span>
					</p>
				</fieldset>
			</div>

			<div style="display: inline-block; text-align:center">
				<input id="btnCreation" type="submit" value="Enregister les modifications" class="bouton">
			</div>
		</fieldset>

	</form>
</body>
</html>