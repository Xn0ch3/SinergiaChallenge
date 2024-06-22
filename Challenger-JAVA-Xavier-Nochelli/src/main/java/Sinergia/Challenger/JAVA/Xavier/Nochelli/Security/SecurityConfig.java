package Sinergia.Challenger.JAVA.Xavier.Nochelli.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de las reglas de autorización para diferentes rutas y métodos HTTP
                .authorizeHttpRequests(auth -> auth

                        //Permitir acceso a recursos estáticos sin Autorización.
                        .requestMatchers("/index.html", "/style.css", "/index.js").permitAll()

                        //Configuracion de Acceso a Usuarios.
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuary/create", "/usuary/forgot-password").permitAll()
                        .requestMatchers("/pages/usuary.html", "/javaScript/usuary.js").hasAnyAuthority("USUARY", "ADMIN")
                        .requestMatchers(HttpMethod.GET,"/usuary/current").hasAnyAuthority("USUARY", "ADMIN")
                        // Denegar acceso a cualquier otra solicitud no configurada expresamente
                        .anyRequest().denyAll()
                )
                // Desactivar CSRF para evitar la necesidad de manejar tokens CSRF en las peticiones cliente-servidor
                .csrf(csrf -> csrf.disable())
                // Desactivar opciones de encabezados para permitir la carga de marcos de H2-Console
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        // Configuración del formulario de login
        http.formLogin( formLogin ->
                formLogin.loginPage("/index.html")
                        .usernameParameter("email")   //Key primer parámetro
                        .passwordParameter("password") //Key Segundo parámetro
                        .loginProcessingUrl("/api/login") //Endpoint para la petición.
                        .successHandler((request, response, authentication) -> clearAuthenticationAttributes(request))
                        .failureHandler(((request, response, exception) -> response.sendError(401, "Client not Fount")))
                        .permitAll()
        );

        // Manejo de excepciones para entradas no autenticadas
        http.exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint((request, response, authException) -> response.sendError(403))
        );
        // Configuración del proceso de logout
        http.logout(logout -> logout
                .logoutUrl("/api/logout") // URL que maneja el proceso de logout
                .deleteCookies("JSESSIONID") // Eliminar cookies específicas al hacer logout
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) // Manejador en caso de logout exitoso
        );
        // Configuración de "Recuérdame" para mantener la sesión activa
        http.rememberMe(Customizer.withDefaults()); // Duración por defoul de la cookie de "Recuérdame"

        return http.build(); // Construir la configuración de seguridad
    }

    // Método para limpiar atributos de error de autenticación al tener un login exitoso
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
