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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "hopital")
public class Hopital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_hopital_id") 
	private int id;

	@NotBlank(message="ce champ ne peut pas être vide")
      
	@Column(name = "nom_hopital")
	private String nom_hopital;

	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[0-9]{10}",message="le téléphone doit être 10 chiffres numerique  ")  
	@Column(name = "n_tele")
	private String n_tele;
	
	@NotBlank(message="ce champ ne peut pas être vide")
	@Column(name = "lieu_hopital")
	private String lieu_hopital;
	
	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[a-zA-Z0-9]*",message=" ")  
	@Size(min=2, max=30, message ="la taille du username doit être comprise entre 3 et 30 lettres")
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
	@JoinTable(name = "auth_hopital_role", joinColumns = @JoinColumn(name = "auth_hopital_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_hopital() {
		return nom_hopital;
	}

	public void setNom_hopital(String nom_hopital) {
		this.nom_hopital = nom_hopital;
	}

	public String getN_tele() {
		return n_tele;
	}

	public void setN_tele(String n_tele) {
		this.n_tele = n_tele;
	}

	public String getLieu_hopital() {
		return lieu_hopital;
	}

	public void setLieu_hopital(String lieu_hopital) {
		this.lieu_hopital = lieu_hopital;
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