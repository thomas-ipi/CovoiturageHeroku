

<form action="itineraire" method="post">
	<fieldset>
		<legend>Recherche</legend>
		<label for="depart">Départ </label>
		 <input type="textarea" name="depart" id="depart" required="required" value="${user.adresse}" readonly size="60"> 
		 <input type="hidden" name="coordlist" id="coordlist" required="required" value="">
		<p>
			<label>Rayon de recherche (en km)<span class="requis">*</span></label>
			<input name="rayon" type="number" size="5" required="required" value="${form['rayon']}" />
		</p>
		<p>
			<label for="depart"> </label> <input class="bouton" type="submit"
				value="rechercher" />
		</p>
	</fieldset>
</form>


