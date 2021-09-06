package com.healthX.security.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.healthX.model.Client;

public class ClientDetailsImpl implements ClientDetails {
	
	private static final long serialVersionUID = 1L;
	private final Client client;

	public ClientDetailsImpl(Client client) {
		this.client = client;
	}

	@Override
	public String getClientId() {
		return client.getName();
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return client.getSecret();
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		return Set.of(client.getScope());
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return client.getGrantTypes().stream().map(g->g.getName()).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return Set.of(client.getRedirectURI());
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Set.of(new SimpleGrantedAuthority(client.getScope()));
	}

	@Override
	public Set<String> getResourceIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
