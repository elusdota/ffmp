package org.craftsmen.ffmp.webclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOGIN_PROCESSING_URL = "/j_spring_security_check";
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests().antMatchers("/js/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                super.handle(request, response, accessDeniedException);
                accessDeniedException.printStackTrace();
            }
        })
                .and()
                        // Login
                .formLogin().loginPage("/login")
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl("/login?error").defaultSuccessUrl("/index").and()
                .logout().logoutUrl("/j_spring_security_logout")
                .invalidateHttpSession(true).logoutSuccessUrl("/login")
                // .and()
                // .headers()
                // .addHeaderWriter(new
                // XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and().csrf().disable();
    }

    /**
     * <b>Remember me</b> implementation.
     *
     * @return The {@link PersistentTokenRepository}.
     */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
