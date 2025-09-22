package com.paymentproject.payment.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.paymentproject.payment.customer.Role;
import com.paymentproject.payment.filters.JwtFilter;
import com.paymentproject.payment.userDetailService.uds;

@Configuration
@EnableWebSecurity
public class securityConfig {
    @Autowired
    uds myuserDetialService ;

    @Autowired
    JwtFilter jf;
    
    @Bean
public SecurityFilterChain SFC(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/bank/admin/createUser/").permitAll()
            .requestMatchers("/bank/admin/**").hasRole(Role.ADMIN.name())
            .requestMatchers("/bank/user/**").hasAnyRole(Role.ADMIN.name(),Role.USER.name())

            .anyRequest().authenticated()

            
        )
        .addFilterBefore(jf, UsernamePasswordAuthenticationFilter.class)
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());

    http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
}


    

    @Bean
    public AuthenticationProvider AP (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        provider.setUserDetailsService(myuserDetialService);
        return provider;
    }


    
}
