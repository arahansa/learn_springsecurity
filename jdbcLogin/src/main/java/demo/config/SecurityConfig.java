package demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired DataSource datsSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datsSource);
	}
	
	// 1.2. http 설정
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()   
			.antMatchers("/admin/**").hasRole("ADMIN")     
			.antMatchers("/**").permitAll()                  
			//.anyRequest().authenticated()                                                   
			.and()
		.formLogin()
			.loginPage("/login") // 2.2 로그인 페이지로 이동시킴
			.permitAll();  
	}

}
