//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.config;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.repositories.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.logger.info("OAuthSuccessHandlerworking");
        DefaultOAuth2User oauthUser = (DefaultOAuth2User)authentication.getPrincipal();
        OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        this.logger.info(authorizedClientRegistrationId);
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRolesList(List.of("ROLE_USER"));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("password");
        if (authorizedClientRegistrationId.equals("google")) {
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setProviderId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("Google is user for registration");
            if (this.userRepo.findByEmail((String)oauthUser.getAttribute("email")).isEmpty()) {
                this.userRepo.save(user);
            }
        } else if (authorizedClientRegistrationId.equals("github")) {
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString() + "@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            String providerId = oauthUser.getName();
            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderId(providerId);
            user.setAbout("Github is used for registration");
            user.setProvider(Providers.GITHUB);
            if (this.userRepo.findByEmail(email).isEmpty()) {
                this.userRepo.save(user);
            }
        } else {
            this.logger.info("unknown provider");
        }

        (new DefaultRedirectStrategy()).sendRedirect(request, response, "/user/dashboard");
    }
}
