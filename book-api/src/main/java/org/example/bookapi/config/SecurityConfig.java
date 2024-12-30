package org.example.bookapi.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig( UserDetailsService userDetailsService ) {

        this.userDetailsService = userDetailsService;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration ) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic( Customizer.withDefaults() );

        http.csrf( c -> c.disable() );

        http.cors( c -> {
            CorsConfigurationSource source = new CorsConfigurationSource() {

                @Override
                public CorsConfiguration getCorsConfiguration( HttpServletRequest request ) {

                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials( true );
                    config.addAllowedOrigin( "http://localhost:3000");
                    config.addAllowedHeader( "*" );
                    config.addAllowedMethod( "*" );
                    config.addExposedHeader( "*" );
                    return config;

                }

            };

            c.configurationSource(source);

        });

//        http.authenticationProvider( daoAuthenticationProvider() );

        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/auth/**").permitAll();
            c.requestMatchers("/api/bookstore/**").permitAll();
            c.anyRequest().authenticated();
        });
        http.csrf(c -> c.disable());

        return http.build();

    }

}
