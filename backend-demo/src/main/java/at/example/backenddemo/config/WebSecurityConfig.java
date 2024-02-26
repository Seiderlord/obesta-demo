package at.example.backenddemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.client-id}")
    private String keyCloakClientId;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().anyRequest().permitAll().and()
            // Enable CORS and disable CSRF
            .cors().and().csrf().disable()
            // Set resource server
            .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(new KeyCloakAuthoritiesExtractor(this.keyCloakClientId));


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("<INSERT-FRONTEND-SERVER-URL>","<INSERT-AUTH-SERVER-URL>"
        ));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(
                List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CACHE_CONTROL,HttpHeaders.CONTENT_TYPE)
        );
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
