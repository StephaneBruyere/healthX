package com.healthX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);
//
//	@Autowired
//	private UserRepository userRepo;
//
//	@Bean
//	public UserDetailsService uds() {
//		return new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				LOGGER.debug("in loadUserByUsername");
//				User user = null;
//				Optional<User> u = userRepo.findUserByUsername(username);
//				if (u.isPresent()) {
//					user = u.get();
//					List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities()
//																				.stream()
//																				.map(a -> new SimpleGrantedAuthority(a.getName()))
//																				.collect(Collectors.toList());
//					return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//				} else
//					throw new UsernameNotFoundException("User not found");
//			}
//		};
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
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
//					.mvcMatchers("/user/**").permitAll()
					.mvcMatchers("/client/**").permitAll()
					.mvcMatchers("/actuator/**").permitAll();
		super.configure(http);
	}

}
