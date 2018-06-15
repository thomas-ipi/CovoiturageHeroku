
<table>
	<tr>
		<th>Email</th>
		<th>Nom</th>
		<th>Prénom</th>
		<th>Adresse</th>
		<th>Distance</th>
		<th></th>
	</tr>
	<c:if test="${empty userstrouves}">
		<tr>
			<td width="20%"></td>
			<td width="20%"></td>
			<td width="10%"></td>
			<td width="208%"></td>
			<td width="15%"></td>
			<td width="15%"></td>
</c:if>

	<c:forEach items="${userstrouves}" var="u" varStatus="row">
		<tr class="${((row.count mod 2)==0)?'pair':'impair'} userstrouves"
			data-marker="${u.value.nomComplet}" data-latlong="${u.value.latlong}"
			data-adresse="${u.value.adresse}">
			<td width="20%">${u.value.email}</td>
			<td width="20%">${u.value.nom}</td>
			<td width="10%">${u.value.prenom}</td>
			<td width="208%">${u.value.adresse}</td>
			<td width="15%">${u.value.km} km.</td>
			<td width="15%" class="action"><input name="check" type="checkbox" value="case" onchange="trouveRoute(true)"></td>
	</c:forEach>
</table>



