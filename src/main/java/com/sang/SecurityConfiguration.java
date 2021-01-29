package com.sang;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sang.service.AdminDetailsService;
import com.sang.service.DonneurDetailsService;
import com.sang.service.HopitalDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private HopitalDetailsService hopitalDetailsService;
	
	@Autowired
	private DonneurDetailsService donneurDetailsService;
	
	@Autowired
	private AdminDetailsService adminDetailsService;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.hopital-query}")
	private String hopitalQuery;
	
	@Value("${spring.queries.admin-query}")
	private String adminQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Value("${spring.queries.role-query}")
	private String roleQuery;
	
	@Value("${spring.queries.rol-query}")
	private String rolQuery;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
		
		auth.jdbcAuthentication().usersByUsernameQuery(hopitalQuery).authoritiesByUsernameQuery(roleQuery)
		.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
		
		auth.jdbcAuthentication().usersByUsernameQuery(adminQuery).authoritiesByUsernameQuery(rolQuery)
		.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// URLs matching for access rights
				.antMatchers("/").permitAll()
				.antMatchers("/registerdonneur").permitAll()
				.antMatchers("/registerhopital").permitAll()
				.antMatchers("/registeradmin").permitAll()
				.antMatchers("/profile").hasAnyAuthority("HOPITAL", "ADMIN", "DONNEUR")
				.antMatchers("/home").permitAll()
				.antMatchers("/blog").permitAll()
				.antMatchers("/update_article").hasAnyAuthority("ADMIN")
				.antMatchers("/contact").permitAll()
				.antMatchers("/all_annonce").permitAll()
				.antMatchers("/update_annonce").hasAnyAuthority("HOPITAL","ADMIN")
				.antMatchers("/update_campagne").hasAnyAuthority("ADMIN")
				.antMatchers("/contact").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/home/**").hasAnyAuthority("HOPITAL", "ADMIN", "DONNEUR")
				//.anyRequest().authenticated()
				.and()
				 //form login
				.csrf().disable().formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/home",true)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
				// logout
				.logout().invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll();	
	}
	
	@Bean
	public AuthenticationProvider authenticationProviderhop()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(hopitalDetailsService);
		return provider;
	}
	@Bean
	public AuthenticationProvider authenticationProviderdonneur()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(donneurDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvideradmin()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(adminDetailsService);
		return provider;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**" , "/img/**", "/css/css/**", "/sass/**");
	}

	
}
