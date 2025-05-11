//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageTypes;
import com.scm.services.EmailService;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @GetMapping({"/"})
    public String index(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() ? "redirect:/user/profile" : "home";
    }

    @GetMapping({"/home"})
    public String home(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() ? "redirect:/user/profile" : "home";
    }

    @RequestMapping({"/about"})
    public String aboutPage(Model model, Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() ? "redirect:/user/profile" : "about";
    }

    @GetMapping({"/services"})
    public String servicesPage() {
        return "services";
    }

    @GetMapping({"/login"})
    public String loginPage(HttpSession session, HttpServletResponse response, Authentication authentication) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        return authentication != null && authentication.isAuthenticated() ? "redirect:/user/profile" : "login";
    }

    @GetMapping({"/register"})
    public String registerPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/user/profile";
        } else {
            model.addAttribute("userForm", new UserForm());
            return "register";
        }
    }

    @PostMapping({"/do-register"})
    public String processRegister(@ModelAttribute @Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("message", Message.builder().content("Please fix the errors below").type(MessageTypes.red).build());
            return "register";
        } else {
            String otp = String.format("%06d", (new Random()).nextInt(999999));
            session.setAttribute("otp", otp);
            session.setAttribute("userForm", userForm);
            this.emailService.sendOtpEmail(userForm.getEmail(), otp);
            return "redirect:/verify-otp?email=" + userForm.getEmail();
        }
    }

    @GetMapping({"/verify-otp"})
    public String verifyOtpPage(@RequestParam(required = false) String email, HttpSession session, Model model) {
        UserForm userForm = (UserForm)session.getAttribute("userForm");
        if (userForm == null) {
            session.setAttribute("message", Message.builder().content("Session expired! Please register again").type(MessageTypes.red).build());
            return "redirect:/register";
        } else {
            String finalEmail = email != null ? email : userForm.getEmail();
            model.addAttribute("email", finalEmail);
            return "verify_otp";
        }
    }

    @PostMapping({"/verify-otp"})
    public String verifyOtp(@RequestParam("otp") String enteredOtp, HttpSession session) {
        String generatedOtp = (String)session.getAttribute("otp");
        UserForm userForm = (UserForm)session.getAttribute("userForm");
        if (generatedOtp != null && userForm != null) {
            if (generatedOtp.equals(enteredOtp)) {
                User user = User.builder().name(userForm.getName()).email(userForm.getEmail()).password(userForm.getPassword()).phoneNumber(userForm.getPhoneNumber()).about(userForm.getAbout()).enabled(true).emailVerified(true).profilePic("https://default-profile-pic.jpg").build();
                this.userService.saveUser(user);
                session.removeAttribute("otp");
                session.removeAttribute("userForm");
                session.setAttribute("message", Message.builder().content("Registration Successful!").type(MessageTypes.green).build());
                return "redirect:/login";
            } else {
                session.setAttribute("message", Message.builder().content("Invalid OTP! Try again").type(MessageTypes.red).build());
                return "redirect:/verify-otp?email=" + userForm.getEmail();
            }
        } else {
            return "redirect:/register";
        }
    }

    @GetMapping({"/resend-otp"})
    public String resendOtp(@RequestParam("email") String email, HttpSession session) {
        UserForm userForm = (UserForm)session.getAttribute("userForm");
        if (userForm != null && userForm.getEmail().equals(email)) {
            LocalDateTime lastOtpTime = (LocalDateTime)session.getAttribute("lastOtpTime");
            if (lastOtpTime != null && lastOtpTime.plusMinutes(1L).isAfter(LocalDateTime.now())) {
                session.setAttribute("message", Message.builder().content("Please wait 1 minute before resending").type(MessageTypes.red).build());
                return "redirect:/verify-otp?email=" + email;
            } else {
                String newOtp = String.format("%06d", (new Random()).nextInt(999999));
                session.setAttribute("otp", newOtp);
                session.setAttribute("lastOtpTime", LocalDateTime.now());
                this.emailService.sendOtpEmail(email, newOtp);
                session.setAttribute("message", Message.builder().content("New OTP sent successfully").type(MessageTypes.green).build());
                return "redirect:/verify-otp?email=" + email;
            }
        } else {
            session.setAttribute("message", Message.builder().content("Session expired! Please register again").type(MessageTypes.red).build());
            return "redirect:/register";
        }
    }

    @GetMapping({"/privacy-policy"})
    public String privacyPolicyPage(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() ? "redirect:/user/profile" : "privacy-policy";
    }

    @GetMapping({"/terms-of-service"})
    public String termsOfServicePage(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() ? "redirect:/user/profile" : "terms-of-service";
    }
}
