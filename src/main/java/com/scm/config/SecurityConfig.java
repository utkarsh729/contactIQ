//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.config;

import com.scm.services.implementation.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler;
    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private CustomContactEntryPoint customContactEntryPoint;

    SecurityConfig(OAuthAuthenticationSuccessHandler OAuthAuthenticationSuccessHandler) {
        this.OAuthAuthenticationSuccessHandler = OAuthAuthenticationSuccessHandler;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider doaAuthenticationProvider = new DaoAuthenticationProvider();
        doaAuthenticationProvider.setUserDetailsService(this.securityCustomUserDetailService);
        doaAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return doaAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorizeRequests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authorizeRequests.requestMatchers(new String[]{"/user/**", "/contact/**"})).authenticated();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authorizeRequests.anyRequest()).permitAll();
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.exceptionHandling((eh) -> eh.authenticationEntryPoint(this.customContactEntryPoint));
        httpSecurity.formLogin((formLogin) -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/dashboard", true);
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            formLogin.failureHandler(this.authFailureHandler);
        });
        httpSecurity.logout((logoutForm) -> {
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });
        httpSecurity.oauth2Login((oauth) -> {
            oauth.loginPage("/login");
            oauth.successHandler(this.OAuthAuthenticationSuccessHandler);
        });
        return (SecurityFilterChain)httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
