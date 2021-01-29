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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "donneur")
public class Donneur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_donneur_id")
	private int id;

	@NotNull(message="First name is compulsory")
	@Column(name = "first_name")
	private String first_name;

	
	@NotNull(message="Last name is compulsory")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message="grp sanguin name is compulsory")
	@Column(name = "grp_s")
	private String grp_s;


	@NotNull(message="date dernier don is compulsory")
	@Column(name = "date_der_don")
	private Date date_der_don;
	
	
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
	@JoinTable(name = "auth_donneur_role", joinColumns = @JoinColumn(name = "auth_donneur_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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