package com.sang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "annonce")
public class Annonce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_annonce")
	private int id_annonce;

	@NotBlank(message="ce champ ne peut pas être vide")
	 
	@Column(name = "titre")
	private String titre;

	@NotBlank(message="ce champ ne peut pas être vide")
	@Column(name = "grp_s")
	private String grp_s;
	
	@NotBlank(message="ce champ ne peut pas être vide")
	 @Pattern(regexp="^[0-9]{10}",message="le numéro de téléphone doit contenir 10 chiffres numerique")  
	@Column(name = "n_tele")
	private String n_tele;
	
	@NotBlank(message="ce champ ne peut pas être vide")
	@Column(name = "lieu_h")
	private String lieu_h;
	
	@NotBlank(message="ce champ ne peut pas être vide")
	@Column(name = "contenu_annonce")
	private String contenu_annonce;
	
	
	

	public Annonce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Annonce(int id_annonce, @NotNull(message = "titre is compulsory") String titre,
			@NotNull(message = "grp_s is compulsory") String grp_s,
			@NotNull(message = "n_tele is compulsory") String n_tele,
			@NotNull(message = "lieu_h is compulsory") String lieu_h,
			@NotNull(message = "contenu_annonce is compulsory") String contenu_annonce) {
		super();
		this.id_annonce = id_annonce;
		this.titre = titre;
		this.grp_s = grp_s;
		this.n_tele = n_tele;
		this.lieu_h = lieu_h;
		this.contenu_annonce = contenu_annonce;
	}

	public int getId_annonce() {
		return id_annonce;
	}

	public void setId_annonce(int id_annonce) {
		this.id_annonce = id_annonce;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getGrp_s() {
		return grp_s;
	}

	public void setGrp_s(String grp_s) {
		this.grp_s = grp_s;
	}

	public String getN_tele() {
		return n_tele;
	}

	public void setN_tele(String n_tele) {
		this.n_tele = n_tele;
	}

	public String getLieu_h() {
		return lieu_h;
	}

	public void setLieu_h(String lieu_h) {
		this.lieu_h = lieu_h;
	}

	public String getContenu_annonce() {
		return contenu_annonce;
	}

	public void setContenu_annonce(String contenu_annonce) {
		this.contenu_annonce = contenu_annonce;
	}
	
}

	
	