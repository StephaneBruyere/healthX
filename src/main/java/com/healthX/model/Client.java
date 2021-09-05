package com.healthX.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String secret;
	private String scope;
	
	@Column(name = "rediect_uri")
	private String redirectURI;
	
	@NotNull
	@OneToMany(fetch = FetchType.EAGER)
	private List<GrantType> grantTypes;

	public Client() {};

}
