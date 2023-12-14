package ua.com.foxminded.cardatabase.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.annotation.Inherited;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@OpenAPIDefinition(info = @Info(title = "cardatabase", version = "0.0.1-SNAPSHOT"))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            .csrf().disable()
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
