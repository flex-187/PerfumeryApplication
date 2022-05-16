package ok.perfumery.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ok.perfumery.dao.UserRepository;
import ok.perfumery.entity.user.User;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null) return user;
			
			throw new UsernameNotFoundException("User '" + username + "' not found");
			
		};
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeRequests()
				.antMatchers("/products/new/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/products/*/edit/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/producers/new/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/producers/*/edit/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/", "/**").permitAll()
				.and()
					.formLogin()
					.loginPage("/login")
				.and()
					.logout()
					 .logoutUrl("/logout")
					.logoutSuccessUrl("/")
				.and()
				.build();
	}

}
