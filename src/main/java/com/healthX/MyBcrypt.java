package com.healthX;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyBcrypt {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String mdp = bCryptPasswordEncoder.encode("mySecretPwd");
		System.out.println(mdp);
		System.out.println(bCryptPasswordEncoder.matches("mySecretPwd", "$2a$10$XwD/h6dw3R2JWTGEs1XDnuNC4GkzOlf/oXbObrbP9xoO/cN6IwFae"));
	}

}
