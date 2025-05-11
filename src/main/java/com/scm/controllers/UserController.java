//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.UserEmail;
import com.scm.services.ContactService;
import com.scm.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @GetMapping({"/dashboard"})
    public String userDashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = UserEmail.getEmailOfLoggedUser(authentication);
            User user = this.userService.getUserByEmail(username);
            List<Contact> allContacts = this.contactService.getAllContactsByUser(user);
            long totalContacts = (long)allContacts.size();
            long favouriteContacts = allContacts.stream().filter(Contact::isFavourite).count();
            List<Contact> upcomingBirthdaysList = this.contactService.getUpcomingBirthdays(user);
            int upcomingBirthdays = upcomingBirthdaysList.size();
            List<Contact> recentActivity = this.contactService.getRecentlyModifiedContacts(user, 5);
            model.addAttribute("totalContacts", totalContacts);
            model.addAttribute("favouriteContacts", favouriteContacts);
            model.addAttribute("upcomingBirthdays", upcomingBirthdays);
            model.addAttribute("upcomingBirthdaysList", upcomingBirthdaysList);
            model.addAttribute("recentActivity", recentActivity);
            return "user/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping({"/profile"})
    public String userProfile(Model model, Authentication authentication) {
        return "user/profile";
    }
}
