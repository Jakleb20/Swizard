package at.kaindorf.htl.ex0025;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration
{

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
  {
    http.authorizeHttpRequests( authorize ->
            authorize.requestMatchers("/register/**").permitAll()
                    .requestMatchers("/","index").permitAll()
                    .requestMatchers("/users").hasRole("ADMIN").anyRequest().denyAll()
    )
    .formLogin( form -> form.loginPage("/login").defaultSuccessUrl("/users").permitAll() )

    .logout( logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll() );
    return http.build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

}


