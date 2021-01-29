package com.sang.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_article")
	private int id_article;
	
	
	@Column(name = "image")
	private String image;
	
	
	@Column(name = "date")
	private Timestamp date;

	@NotNull(message="titre is compulsory")
	@Column(name = "titre")
	private String titre;
	
	@NotNull(message="contenu is compulsory")
	@Column(name = "contenu")
	private String contenu;

	

	public int getId_article() {
		return id_article;
	}

	public void setId_article(int id_article) {
		this.id_article = id_article;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp Date) {
		this.date = Date;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

}

	
	