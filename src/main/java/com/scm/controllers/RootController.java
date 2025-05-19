//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helpers.UserEmail;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserService userService;
    Logger logger = LoggerFactory.getLogger(RootController.class);

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            System.out.println("Not authenticated");
        } else {
            String username = UserEmail.getEmailOfLoggedUser(authentication);
            this.logger.info("user logged in : {}", username);
            System.out.println("user Profile");
            User user = this.userService.getUserByEmail(username);
            System.out.println(user);
            this.logger.info(user.getName());
            this.logger.info(user.getEmail());
            model.addAttribute("loggedInUser", user);
        }
    }
}
