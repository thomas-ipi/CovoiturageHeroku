package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity 
public class UserBean {
	
	@Id
	@GeneratedValue
	private long id;
	private String profil; // conducteur, passager
	private String email;
	private String nom;
	private String prenom;
	private String adresse1;
	private String adresse2;
	private String codepostal;
	private String ville;
	private String genre;
	private String fumeur;
	private String telFixe;
	private String telPortale;
	private String password;
	private String latlong;
	private String avatar;
	@Transient
	private long km;
	

	public UserBean() {
		super();
	}
		
	public UserBean(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	



	public UserBean(long id, String profil, String email, String nom,
			String prenom, String adresse1, String adresse2, String codepostal,
			String ville, String genre, String fumeur, String telFixe,
			String telPortale, String password, String latlong, String avatar) {
		super();
		this.id = id;
		this.profil = profil;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codepostal = codepostal;
		this.ville = ville;
		this.genre = genre;
		this.fumeur = fumeur;
		this.telFixe = telFixe;
		this.telPortale = telPortale;
		this.password = password;
		this.latlong = latlong;
		this.avatar = avatar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse1() {
		return adresse1;
	}
	
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getFumeur() {
		return fumeur;
	}
	public void setFumeur(String fumeur) {
		this.fumeur = fumeur;
	}
	
	public String getTelFixe() {
		return telFixe;
	}
	public void setTelFixe(String telFixe) {
		this.telFixe = telFixe;
	}
	
	public String getTelPortale() {
		return telPortale;
	}
	public void setTelPortale(String telPortale) {
		this.telPortale = telPortale;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getLatlong() {
		return latlong;
	}

	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public long getKm() {
		return km;
	}

	public void setKm(long km) {
		this.km = km;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public String getAdresse() {
		return ((adresse1!=null)? adresse1 : "") + " " + ((adresse2!=null)?  adresse2 : "") + " " + ((codepostal!=null)? codepostal : "") + " " + ((ville!=null)? ville : "") ;  
	}
	

	public String getNomComplet() {
		return ((nom!=null)? nom : "") + " " + ((prenom!=null)?  prenom : "");  
	}	

}
