package com.healthX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthX.dao.GrantTypeRepository;
import com.healthX.model.GrantType;

@Service
public class GrantTypeService {
	
	@Autowired
	GrantTypeRepository grantTypeRepository;
	
	public void addGrantType(GrantType gt) {
		Optional<GrantType> a = grantTypeRepository.findGrantTypeByName(gt.getName());

		if (a.isEmpty()) {
			grantTypeRepository.save(gt);
		} else {
			throw new RuntimeException("Authority already exists!");
		}
	}
	
	public GrantType getGrantTypeByName(String name) {
		return grantTypeRepository.findGrantTypeByName(name).orElseThrow();
	}

}
