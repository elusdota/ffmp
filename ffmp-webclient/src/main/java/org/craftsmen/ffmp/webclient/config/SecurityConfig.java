package org.craftsmen.ffmp.webclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOGIN_PROCESSING_URL = "/j_spring_security_check";
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        // @formatter:off
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/js/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/images/**")
                .permitAll()
                .antMatchers("/bower_components/**")
                .permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/patchca.png").permitAll()
                .anyRequest().authenticated();
        http.httpBasic().disable();
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .usernameParameter("username") // default is username
                .passwordParameter("password")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index", true)
                .and().csrf().disable();
        http.logout()
                .logoutUrl("/j_spring_security_logout")
                .invalidateHttpSession(true).logoutSuccessUrl("/login")
                .and().csrf().disable();
        http.exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
//        http
//         .headers()
//         .addHeaderWriter(new
//         XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
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
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
