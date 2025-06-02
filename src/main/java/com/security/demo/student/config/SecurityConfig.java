package com.security.demo.student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //this will tells spring from where we get details of user
    @Autowired
    private UserDetailsService userDetailsService;

    //Ye code Spring Security ko batata hai ki user data kaise milega aur password kaise verify hoga.
    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();   //takes user data from userDetailService,password match krta hai,role check krta hai

        provider.setUserDetailsService(userDetailsService); //tells spring to use customDetailSerive for user Data
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //password will be converted  in plain text for checking with original password

        return provider;    //spring security use this object during login time
    }



    //There is some default configuration of spring security.
    //If we want to change the default configuration of security we should return the object
    // of SecurityFilterChange().

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //1. Creating first change in security i.e. disabling the csrf token.
        http.csrf(customizer->customizer.disable());

        //2.Koi bhi request(any API call) aye uske liye user ko ab authenticate hona zaroori hai
        http.authorizeHttpRequests(request->request.anyRequest().authenticated());

        //3. If we are using custom security then we need to implement this to enable form based login
        // If we are having 5th step code then we don't need form based login and we can remove it
//        http.formLogin(Customizer.withDefaults());


        //4. Enabling login with username and password in basic auth
        http.httpBasic(Customizer.withDefaults());

        //5. It tells do not store any session on the server i.e. every request should carry its own authentication
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

    //how to manage diffrent users in the application
    //If we are using this userServiceDetails() class then we cannot login with the details of .properties user
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User
                                .withDefaultPasswordEncoder()
                                .username("mdgulfam")
                                .password("khan@123")
                                .roles("USER")
                                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("mdkaif")
                .password("kaif@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
