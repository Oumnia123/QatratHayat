package com.sang.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_admin_id")
	private int id;

	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[a-zA-Z]*",message="le nom doit être alphabetique ")  
    @Column(name = "nom")
	private String nom;

	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[a-zA-Z]*",message="le prenom doit être alphabetique ")  
	@Column(name = "prenom")
	private String prenom;
	
	@NotBlank(message="ce champ ne peut pas être vide")
	@Column(name = "username")
	private String username;

	@NotBlank(message="ce champ ne peut pas être vide")
	@ValidPassword(message="le mot de passe doit contenir au moins une lettre majuscule, une lettre miniscule, un chiffre, un caractère special, et au moins 8 caractères ou plus ")
	@Column(name = "password")
	private String password;


	@Column(name = "status")
	private String status;
	
	@Column(name = "image")
	private String image;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "auth_admin_role", joinColumns = @JoinColumn(name = "auth_admin_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
	
}