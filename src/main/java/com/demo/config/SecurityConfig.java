package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //permitir h2-console
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        //protección de rutas
        http.authorizeHttpRequests(auth -> auth
                // 1. Recursos Públicos y Autenticación
                .requestMatchers("/register", "/login", "/css/**", "/webjars/**", "/images/**").permitAll()

                // 2. Control de Películas (MovieController)
                .requestMatchers(HttpMethod.GET, "/movies", "/movies/{id}").permitAll()
                .requestMatchers("/movies/new", "/movies/edit/**", "/movies/deactivate/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")

                // 3. Control de Salas (RoomController)
                .requestMatchers(HttpMethod.GET, "/salas", "/salas/{id}").permitAll()
                .requestMatchers("/salas/new", "/salas/edit/**", "/salas/deactivate/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/salas").hasRole("ADMIN")

                // 4. Control de Sesiones / Proyecciones (SessionController - ¡Faltaba!)
                .requestMatchers(HttpMethod.GET, "/sessions", "/sessions/{id}").permitAll() // Público para que vean la cartelera/horarios
                .requestMatchers("/sessions/new", "/sessions/edit/**", "/sessions/delete/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/sessions").hasRole("ADMIN")

                // 5. Control de Reseñas (ReviewController)
                .requestMatchers(HttpMethod.GET, "/reviews", "/reviews/{id}").permitAll()
                .requestMatchers("/reviews/new", "/reviews/edit/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/reviews").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/reviews/disable/**", "/reviews/delete/**").hasRole("ADMIN")

                // 6. Control de Tickets / Compras (TicketController y Órdenes)
                .requestMatchers(HttpMethod.GET, "/tickets", "/tickets/{id}").authenticated() // Ver todos los tickets es de Admin
                .requestMatchers("/tickets/edit/**").hasRole("ADMIN")
                .requestMatchers("/tickets/buy/**").authenticated() // Comprar requiere estar logueado

                // 7. Cualquier otra ruta no especificada requerirá estar autenticado
                .anyRequest().authenticated()
        );
        //http.exceptionHandling(exception -> exception.accessDeniedPage("/movies"));

        http.exceptionHandling(ex -> ex
                .accessDeniedHandler((request, response, e) ->
                        response.sendRedirect(request.getContextPath() + "/movies"))
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/movies", true)
                .permitAll()
        );

        return http.build();
    }
}