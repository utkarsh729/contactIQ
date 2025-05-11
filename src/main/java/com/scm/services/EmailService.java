//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.services;

import com.scm.forms.ContactMeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your otp for email verification");
        message.setText("Your verification OTP is: " + otp + "\nThis OTP is valid for 10 minutes.");
        this.mailSender.send(message);
    }

    public void sendContactMessage(ContactMeForm contactMeForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("21bcs7248@gmail.com");
        message.setSubject("New Contact Message from " + contactMeForm.getName());
        String var10001 = contactMeForm.getMessage();
        message.setText(var10001 + "From: " + contactMeForm.getEmail() + "\n\n");
        this.mailSender.send(message);
    }
}
