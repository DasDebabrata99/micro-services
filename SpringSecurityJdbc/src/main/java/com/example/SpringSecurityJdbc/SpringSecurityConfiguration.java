package com.example.SpringSecurityJdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {

	@Autowired
	DataSource dataSource;
/*
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("user")
				.roles("USER").build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.createUser(user);
		return users;
	}*/
	
	 // Create UserDetailsManager Bean and let Spring handle the tables
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        UserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Create a default user if needed
        if (!userDetailsManager.userExists("user")) {
            userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("user")
                    .roles("USER")
                    .build()
            );
        }
        
        if (!userDetailsManager.userExists("admin")) {
            userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build()
            );
        }
        
        return userDetailsManager;
    }

    // Configure the authentication manager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }	

/*	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
				.passwordEncoder(NoOpPasswordEncoder.getInstance()) // Allows plain text passwords
				.withUser(User.withUsername("user").password("user").roles("USER"))
				.withUser(User.withUsername("admin").password("admin").roles("ADMIN"));

		return authenticationManagerBuilder.build();
	}
*/
	/*
	 * @Override public void configure(AuthenticationManagerBuilder authBuilder)
	 * throws Exception { authBuilder.jdbcAuthentication() .dataSource(dataSource)
	 * .withDefaultSchema() .withUser( User.withUsername("user") .password("user")
	 * .roles("USER") ) .withUser( User.withUsername("admin") .password("admin")
	 * .roles("ADMIN") ); }
	 */
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.requestMatchers("/user").hasRole("USER")
			.requestMatchers("/admin").hasRole("ADMIN")
			.requestMatchers("/").permitAll().and().formLogin();
		return http.build();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
