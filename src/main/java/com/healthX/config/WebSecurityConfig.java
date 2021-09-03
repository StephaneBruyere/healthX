package com.healthX.config;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.healthX.dao.UserRepository;
import com.healthX.model.User;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private UserRepository userRepo;

	@Bean
	public UserDetailsService uds() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				LOGGER.debug("in loadUserByUsername");
				User user = null;
				Optional<User> u = userRepo.findUserByUsername(username);
				if (u.isPresent()) {
					user = u.get();
					List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities()
																				.stream()
																				.map(a -> new SimpleGrantedAuthority(a.getName()))
																				.collect(Collectors.toList());
					return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
				} else
					throw new UsernameNotFoundException("User not found");
			}
		};

//		var uds = new InMemoryUserDetailsManager();
//		var u = User.withUsername("stef").password("pwd").authorities("read").build();
//		uds.createUser(u);
//		return uds;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
//		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(c -> c.ignoringAntMatchers("/actuator/**", "/user/**", "/client/**"));
		http.authorizeRequests()
					.mvcMatchers("/user/**").permitAll()
					.mvcMatchers("/client/**").permitAll()
					.mvcMatchers("/actuator/**").permitAll();
		super.configure(http);
	}

}
