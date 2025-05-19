//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.controllers;

import com.opencsv.CSVWriter;
import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageTypes;
import com.scm.helpers.UserEmail;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/user/contacts"})
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping({"/add"})
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(
            value = {"/add"},
            method = {RequestMethod.POST}
    )
    public String saveContact(@ModelAttribute @Valid ContactForm contactForm, BindingResult rBindingResult, Authentication authentication, HttpSession session) {
        if (rBindingResult.hasErrors()) {
            Message message = Message.builder().content("Please fill the details correctly").type(MessageTypes.red).build();
            session.setAttribute("message", message);
            return "user/add_contact";
        } else {
            String username = UserEmail.getEmailOfLoggedUser(authentication);
            User user = this.userService.getUserByEmail(username);
            Contact contact = new Contact();
            contact.setName(contactForm.getName());
            contact.setEmail(contactForm.getEmail());
            contact.setAddress(contactForm.getAddress());
            contact.setDescription(contactForm.getDescription());
            contact.setFavourite(contactForm.isFavourite());
            contact.setBirthday(contactForm.getBirthday());
            contact.setInstaLink(contactForm.getInstaLink());
            contact.setLinkedInLink(contactForm.getLinkedInLink());
            contact.setPhoneNumber(contactForm.getPhoneNumber());
            contact.setUser(user);
            if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
                String fileName = UUID.randomUUID().toString();
                String fileURL = this.imageService.uploadImage(contactForm.getContactImage(), fileName);
                contact.setPicture(fileURL);
                contact.setCloudinaryImagePublicId(fileName);
            }

            this.contactService.saveContact(contact);
            Message message = Message.builder().content("Contact Added Successfully").type(MessageTypes.green).build();
            session.setAttribute("message", message);
            return "redirect:/user/contacts/add";
        }
    }

    @RequestMapping
    public String viewContacts(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "8") int size, @RequestParam(value = "sortBy",defaultValue = "name") String sortBy, @RequestParam(value = "direction",defaultValue = "desc") String direction, Model model, Authentication authentication) {
        String username = UserEmail.getEmailOfLoggedUser(authentication);
        User user = this.userService.getUserByEmail(username);
        Page<Contact> pageContacts = this.contactService.getByUser(user, page, size, sortBy, direction);
        model.addAttribute("pageContacts", pageContacts);
        model.addAttribute("pageSize", 8);
        return "user/contacts";
    }

    @RequestMapping({"/search"})
    public String SearchHandler(@RequestParam("field") String field, @RequestParam("keyword") String keyword, @RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "8") int size, @RequestParam(value = "sortBy",defaultValue = "name") String sortBy, @RequestParam(value = "direction",defaultValue = "asc") String direction, Model model, Authentication authentication, HttpSession session) {
        Page<Contact> pageContacts = null;
        String username = UserEmail.getEmailOfLoggedUser(authentication);
        User user = this.userService.getUserByEmail(username);
        if (field.equalsIgnoreCase("name")) {
            pageContacts = this.contactService.searchByName(keyword, page, size, sortBy, direction, user);
        }

        if (field.equalsIgnoreCase("email")) {
            pageContacts = this.contactService.searchByEmail(keyword, page, size, sortBy, direction, user);
        }

        if (field.equalsIgnoreCase("phoneNumber")) {
            pageContacts = this.contactService.searchByphoneNumber(keyword, page, size, sortBy, direction, user);
        }

        model.addAttribute("pageContacts", pageContacts);
        model.addAttribute("pageSize", 8);
        return "user/search";
    }

    @GetMapping({"/delete/{id}"})
    public String deleteContact(@PathVariable String id) {
        this.contactService.deleteContact(id);
        return "redirect:/user/contacts";
    }

    @GetMapping({"/view/{id}"})
    public String viewContact(@PathVariable String id, Model model) {
        Contact contact = this.contactService.getById(id);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setAddress(contact.getAddress());
        contactForm.setBirthday(contact.getBirthday());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavourite(contact.isFavourite());
        contactForm.setInstaLink(contact.getInstaLink());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setPicture(contact.getPicture());
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("id", id);
        return "user/update_contact";
    }

    @PostMapping({"/update/{id}"})
    public String updateContact(@PathVariable("id") String id, @ModelAttribute @Valid ContactForm contactForm, BindingResult rBindingResult, HttpSession session) {
        if (rBindingResult.hasErrors()) {
            Message message = Message.builder().content("Please fill the details correctly").type(MessageTypes.red).build();
            session.setAttribute("message", message);
            return "user/update_contact";
        } else {
            Contact contact = this.contactService.getById(id);
            contact.setId(id);
            contact.setName(contactForm.getName());
            contact.setPhoneNumber(contactForm.getPhoneNumber());
            contact.setEmail(contactForm.getEmail());
            contact.setBirthday(contactForm.getBirthday());
            contact.setAddress(contactForm.getAddress());
            contact.setDescription(contactForm.getDescription());
            contact.setInstaLink(contactForm.getInstaLink());
            contact.setLinkedInLink(contactForm.getLinkedInLink());
            contact.setFavourite(contactForm.isFavourite());
            if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
                String fileName = UUID.randomUUID().toString();
                String imageUrl = this.imageService.uploadImage(contactForm.getContactImage(), fileName);
                contact.setCloudinaryImagePublicId(fileName);
                contact.setPicture(imageUrl);
            }

            this.contactService.updateContact(contact);
            Message message = Message.builder().content("Contact Updated Successfully").type(MessageTypes.green).build();
            session.setAttribute("message", message);
            return "redirect:/user/contacts/view/" + id;
        }
    }

    @GetMapping({"/export/csv"})
    public String exportAllContacts(HttpSession session, HttpServletResponse response, Authentication authentication) {
        try {
            String username = UserEmail.getEmailOfLoggedUser(authentication);
            User user = this.userService.getUserByEmail(username);
            List<Contact> contacts = this.contactService.getAllContactsByUser(user);
            if (contacts == null || contacts.isEmpty()) {
                session.setAttribute("message", Message.builder().content("No contacts added").type(MessageTypes.red).build());
                return "redirect:/user/contacts";
            }

            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter);
            String[] header = new String[]{"Name", "Email", "Phone", "Address", "Birthday", "Instagram", "LinkedIn", "Favourite", "Description"};
            csvWriter.writeNext(header);

            for(Contact contact : contacts) {
                String birthday = contact.getBirthday() != null ? contact.getBirthday().toString() : "";
                String[] row = new String[]{contact.getName(), contact.getEmail(), contact.getPhoneNumber(), contact.getAddress(), birthday, contact.getInstaLink(), contact.getLinkedInLink(), contact.isFavourite() ? "yes" : "no", contact.getDescription()};
                csvWriter.writeNext(row);
            }

            csvWriter.close();
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=contacts.csv");
            response.getWriter().write(stringWriter.toString());
        } catch (IOException var15) {
            IOException e = var15;
            if (!response.isCommitted()) {
                response.setStatus(500);

                try {
                    response.getWriter().write("Error generating CSV: " + e.getMessage());
                } catch (IOException var14) {
                }
            }
        }

        return null;
    }

    @GetMapping({"/sendEmail"})
    public String emailTheContact(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "8") int size, @RequestParam(value = "sortBy",defaultValue = "name") String sortBy, @RequestParam(value = "direction",defaultValue = "desc") String direction, Model model, HttpSession session, Authentication authentication) {
        String username = UserEmail.getEmailOfLoggedUser(authentication);
        User user = this.userService.getUserByEmail(username);
        Page<Contact> pageContacts = this.contactService.getByUser(user, page, size, sortBy, direction);
        model.addAttribute("pageContacts", pageContacts);
        model.addAttribute("pageSize", 8);
        return "user/emailTheContact";
    }

    @RequestMapping({"/searchEmail"})
    public String searchEmailContact(@RequestParam("field") String field, @RequestParam("keyword") String keyword, @RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "8") int size, @RequestParam(value = "sortBy",defaultValue = "name") String sortBy, @RequestParam(value = "direction",defaultValue = "asc") String direction, Model model, Authentication authentication, HttpSession session) {
        Page<Contact> pageContacts = null;
        String username = UserEmail.getEmailOfLoggedUser(authentication);
        User user = this.userService.getUserByEmail(username);
        if (field.equalsIgnoreCase("name")) {
            pageContacts = this.contactService.searchByName(keyword, page, size, sortBy, direction, user);
        }

        if (field.equalsIgnoreCase("email")) {
            pageContacts = this.contactService.searchByEmail(keyword, page, size, sortBy, direction, user);
        }

        if (field.equalsIgnoreCase("phoneNumber")) {
            pageContacts = this.contactService.searchByphoneNumber(keyword, page, size, sortBy, direction, user);
        }

        model.addAttribute("pageContacts", pageContacts);
        model.addAttribute("pageSize", 8);
        return "user/searchEmailContact";
    }

    @PostMapping({"/sendEmail"})
    public ResponseEntity<?> sendEmail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("message") String message, @RequestParam(value = "attachment",required = false) MultipartFile attachment) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, attachment != null);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, false);
            if (attachment != null && !attachment.isEmpty()) {
                String originalFilename = attachment.getOriginalFilename();
                if (originalFilename != null) {
                    helper.addAttachment(originalFilename, new ByteArrayResource(attachment.getBytes()));
                }
            }

            this.mailSender.send(mimeMessage);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email: " + e.getMessage());
        }
    }
}
