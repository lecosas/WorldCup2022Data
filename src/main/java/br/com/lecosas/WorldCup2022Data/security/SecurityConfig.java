package br.com.lecosas.WorldCup2022Data.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // disable to work in Postman.
                .authorizeRequests()
                .anyRequest().permitAll()
                //    .antMatchers("/design", "/orders").hasRole("USER")
                //    .antMatchers(HttpMethod.POST, "/api/ingredients")
                //        .hasAuthority("SCOPE_writeIngredients")
                //    .antMatchers(HttpMethod.DELETE, "/api/ /ingredients")
                //        .hasAuthority("SCOPE_deleteIngredients")
                //    .antMatchers("/", "/**").permitAll()
                //.and()
                //    .formLogin()
                 //       .loginPage("/login")
                 //       .defaultSuccessUrl("/design")
                //.and()
                //    .logout()
                .and()
                .build();
    }
    
}
