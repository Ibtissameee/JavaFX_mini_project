package application;

public class etudiant {
	String nom;
	String prenom;
	String naissance;
	String CNI;
	String CNE;
	public etudiant() {super();}
 public etudiant(String nom,String prenom,String naissance,String CNI,String CNE) {
	 this.nom = nom;
	 this.prenom = prenom;
	 this.naissance = naissance;
	 this.CNI = CNI;
	 this.CNE = CNE;
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
public String getNaissance() {
	return naissance;
}
public void setNaissance(String naissance) {
	this.naissance = naissance;
}
public String getCNI() {
	return CNI;
}
public void setCNI(String CNI) {
	this.CNI = CNI;
}
public String getCNE() {
	return CNE;
}
public void setCNE(String CNE) {
	this.CNE = CNE;
}
 
}