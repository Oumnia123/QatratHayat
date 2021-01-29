package com.sang.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DonneurPrincipal implements UserDetails {
	
	private Donneur donneur;
	
	public DonneurPrincipal(Donneur donneur) {
		super();
		this.donneur = donneur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("DONNEUR"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return donneur.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return donneur.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
