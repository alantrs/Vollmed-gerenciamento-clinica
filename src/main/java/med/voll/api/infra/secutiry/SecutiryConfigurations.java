package med.voll.api.infra.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configuração Spring que marca esta classe como uma classe de configuração
@Configuration
// Habilita a segurança web no aplicativo Spring
@EnableWebSecurity
public class SecutiryConfigurations {

    // Método que define e configura o filtro de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desabilita a proteção CSRF para evitar ataques de falsificação de solicitação entre sites
        return http.csrf().disable()
                // Define a política de gerenciamento de sessão como stateless, o que significa que não haverá sessões mantidas no lado do servidor
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Encerra a configuração do filtro de segurança
                .and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
