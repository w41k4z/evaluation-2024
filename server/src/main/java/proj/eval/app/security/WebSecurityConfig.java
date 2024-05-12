package proj.eval.app.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import proj.eval.app.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Value("${cors.allowed.origin.pattern}")
  private String ALLOWED_ORIGIN_PATTERN;

  private JwtAuthFilter jwtAuthFilter;

  public WebSecurityConfig(JwtAuthFilter jwtAuthFilter) {
    this.jwtAuthFilter = jwtAuthFilter;
  }

  // CORS
  @Bean
  WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
          .addMapping("/**")
          .allowedMethods(CorsConfiguration.ALL)
          .allowedHeaders(CorsConfiguration.ALL)
          .allowedOriginPatterns(ALLOWED_ORIGIN_PATTERN);
      }
    };
  }

  // Security
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .exceptionHandling(exceptionHandling ->
        exceptionHandling.authenticationEntryPoint(
            (request, response, authException) ->
          response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            authException.getMessage()
          )
        )
      )
      .authorizeHttpRequests(authorize ->
        authorize
          // Public routes
          .requestMatchers("/error", "/auth/**")
          .permitAll()
          // Private routes
          .requestMatchers("/api/admin/**")
          .hasAuthority("ADMIN")
          .requestMatchers("/api/user/**")
          .hasAuthority("USER")
          // Authenticated routes
          .anyRequest()
          .authenticated()
      )
      .sessionManagement(management ->
        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class
      );
    return http.build();
  }

  // Spring security has been overriden so we need to provide explicitly our AuthenticationManager
  @Bean
  AuthenticationManager authenticationManager(
    AuthenticationConfiguration authConfig
  ) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  // Spring security has been overriden so we need to provide explicitly our PasswordEncoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
