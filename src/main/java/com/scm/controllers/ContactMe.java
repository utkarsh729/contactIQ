//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.controllers;

import com.scm.forms.ContactMeForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageTypes;
import com.scm.services.EmailService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactMe {
    @Autowired
    private EmailService emailService;

    @GetMapping({"/contact"})
    public String contactPage(Model model, HttpSession session) {
        model.addAttribute("contactMeForm", new ContactMeForm());
        return "contact";
    }

    @PostMapping({"/contact"})
    public String handleContactForm(@ModelAttribute @Valid ContactMeForm contactMeForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("message", Message.builder().content("Please fix the errors in the form").type(MessageTypes.red).build());
            return "contact";
        } else {
            try {
                this.emailService.sendContactMessage(contactMeForm);
                session.setAttribute("message", Message.builder().content("Your message has been sent successfully!").type(MessageTypes.green).build());
            } catch (Exception var5) {
                session.setAttribute("message", Message.builder().content("Failed to send message. Please try again later.").type(MessageTypes.red).build());
            }

            return "redirect:/contact";
        }
    }
}
