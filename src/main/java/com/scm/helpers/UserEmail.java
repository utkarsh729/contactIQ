//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserEmail {
    public static String getEmailOfLoggedUser(Authentication authentication) {
        String username = "";
        if (authentication instanceof OAuth2AuthenticationToken oauth2AuthenticationToken) {
            String clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
            OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
        } else {
            System.out.println("Getting data from local database");
            username = authentication.getName();
        }

        return username;
    }
}
