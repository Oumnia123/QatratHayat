package com.sang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "annonce")
public class Annonce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_annonce")
	private int id_annonce;

	@NotNull(message="titre is compulsory")
	@Column(name = "titre")
	private String titre;

	
	@NotNull(message="contenu_annonce is compulsory")
	@Column(name = "contenu_annonce")
	private String contenu_annonce;


	/**
	 * @return the id_annonce
	 */
	public int getId_annonce() {
		return id_annonce;
	}


	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}


	/**
	 * @return the contenu_annonce
	 */
	public String getContenu_annonce() {
		return contenu_annonce;
	}


	/**
	 * @param id_annonce the id_annonce to set
	 */
	public void setId_annonce(int id_annonce) {
		this.id_annonce = id_annonce;
	}


	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}


	/**
	 * @param contenu_annonce the contenu_annonce to set
	 */
	public void setContenu_annonce(String contenu_annonce) {
		this.contenu_annonce = contenu_annonce;
	}
	
}

	
	