package ua.com.foxminded.cardatabase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                .requestMatchers("/**").authenticated()
            )
            .cors(withDefaults())
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(withDefaults())
            )
            .build();
    }
}
