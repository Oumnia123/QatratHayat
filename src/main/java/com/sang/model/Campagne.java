package com.sang.model;

import java.sql.Date;

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
@Table(name = "campagne")
public class Campagne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_campagne")
	private int id_campagne;
	
	@NotBlank(message="Ce champ ne peut pas être vide")
	@Column(name = "nom")
	private String nom;
	
	@NotBlank(message="Ce champ ne peut pas être vide")
	@Column(name = "lieu")
	private String lieu;
	
	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[0-9]{10}",message="le numéro de téléphone doit contenir 10 chiffres numerique")  
	@Column(name = "telephone")
	private String telephone;
	
	@NotNull(message="ce champ ne peut pas être vide")
	@Column(name = "date")
	private Date date;

	public int getId_campagne() {
		return id_campagne;
	}

	public void setId_campagne(int id_campagne) {
		this.id_campagne = id_campagne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
