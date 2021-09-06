package com.healthX.security.model;

import org.springframework.security.core.GrantedAuthority;

import com.healthX.model.Authority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private final Authority authority;

	public GrantedAuthorityImpl(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority.getName();
	}
}
