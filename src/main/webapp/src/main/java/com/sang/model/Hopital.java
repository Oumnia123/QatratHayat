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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "hopital")
public class Hopital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_hopital_id")
	private int id;

	@NotNull(message="nom hopital is compulsory")
	@Column(name = "nom_hopital")
	private String nom_hopital;

	
	@NotNull(message="lieu hopital is compulsory")
	@Column(name = "lieu_hopital")
	private String lieu_hopital;
	
	@NotNull(message="Email is compulsory")
	@Email(message = "Email is invalid")
	@Column(name = "email")
	private String email;

	@NotNull(message="Password is compulsory")
	@Length(min=8, message="Password should be at least 8 characters")
	@Column(name = "password")
	private String password;


	@Column(name = "status")
	private String status;

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

	public String getLieu_hopital() {
		return lieu_hopital;
	}

	public void setLieu_hopital(String lieu_hopital) {
		this.lieu_hopital = lieu_hopital;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	
}