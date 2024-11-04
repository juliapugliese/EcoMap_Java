package br.com.fiap.ecoMap.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerifyToken verificarToken;

    //@Bean quer que o spring gerencie
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"auth/login").permitAll()

                        .requestMatchers(HttpMethod.GET,"api/usuarios").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"api/denuncias").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/denuncias").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/denuncias").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/denuncias").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"api/localizacoes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/localizacoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/localizacoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/localizacoes").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"api/areas").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/areas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/areas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/areas").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"api/drones").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/drones").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/drones").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/drones").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"api/coletas").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/coletas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/coletas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/coletas").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"api/residuos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/residuos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/residuos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/residuos").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    //configurando authentication manager:
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }

    //criando o codificador de senhas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
