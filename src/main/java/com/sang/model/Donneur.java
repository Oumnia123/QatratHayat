package com.sang.model;

import java.sql.Date;
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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "donneur")
public class Donneur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_donneur_id")
	private int auth_donneur_id;

	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[a-zA-Z]*",message="le nom doit être alphabetique ")  
	@Size(min=2, max=30, message ="la taille du nom doit être comprise entre 3 et 30 lettres")
	@Column(name = "first_name")
	private String first_name;

	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[a-zA-Z]*",message="le prenom doit être alphabetique ")  
	@Size(min=2, max=30, message ="la taille du prénom doit être comprise entre 3 et 30 lettres")
	@Column(name = "last_name")
	private String last_Name;
	
	@NotBlank 
	@Column(name = "grp_s")
	private String grp_s;

	
	//@Past(message="La date de votre dernier don doit être au passé pas au futur!")
	//@NotNull(message="ce champ ne peut pas être vide")
	@Column(name = "date_der_don")
	private Date date_der_don;
	
	
	@NotBlank(message="ce champ ne peut pas être vide")
    @Pattern(regexp="^[a-zA-Z0-9]*",message=" ")  
	@Size(min=2, max=30, message ="la taille du nom dutilisateur doit être comprise entre 3 et 30 lettres")
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
	@JoinTable(name = "auth_donneur_role", joinColumns = @JoinColumn(name = "auth_donneur_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public int getAuth_donneur_id() {
		return auth_donneur_id;
	}

	public void setAuth_donneur_id(int auth_donneur_id) {
		this.auth_donneur_id = auth_donneur_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getGrp_s() {
		return grp_s;
	}

	public void setGrp_s(String grp_s) {
		this.grp_s = grp_s;
	}

	public Date getDate_der_don() {
		return date_der_don;
	}

	public void setDate_der_don(Date date_der_don) {
		this.date_der_don = date_der_don;
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