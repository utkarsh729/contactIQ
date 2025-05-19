//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class ApiController {
    @Autowired
    private ContactService contactService;

    @GetMapping({"/contact/{contactId}"})
    public Contact getContact(@PathVariable String contactId) {
        return this.contactService.getById(contactId);
    }
}
