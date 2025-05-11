//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.services.implementation;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;

    public Contact saveContact(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return (Contact)this.contactRepo.save(contact);
    }

    public Contact updateContact(Contact contact) {
        Contact contactOld = (Contact)this.contactRepo.findById(contact.getId()).orElseThrow(() -> new ResourceNotFoundException("Contact Not Found"));
        contactOld.setAddress(contact.getAddress());
        contactOld.setName(contact.getName());
        contactOld.setPhoneNumber(contact.getPhoneNumber());
        contactOld.setEmail(contact.getEmail());
        contactOld.setFavourite(contact.isFavourite());
        contactOld.setInstaLink(contact.getInstaLink());
        contactOld.setLinkedInLink(contact.getLinkedInLink());
        contactOld.setBirthday(contact.getBirthday());
        contactOld.setDescription(contact.getDescription());
        contactOld.setPicture(contact.getPicture());
        contactOld.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
        return (Contact)this.contactRepo.save(contactOld);
    }

    public List<Contact> getAllContact() {
        return this.contactRepo.findAll();
    }

    public Contact getById(String id) {
        return (Contact)this.contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Not found"));
    }

    public void deleteContact(String id) {
        Contact contact = (Contact)this.contactRepo.findById(id).orElse((Contact) null);
        if (contact != null) {
            this.contactRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Contact Not found with given Id");
        }
    }

    public List<Contact> searchContact(String name, String email, String phoneNumber) {
        return List.of();
    }

    public List<Contact> getByUserId(String userId) {
        return this.contactRepo.findByUserId(userId);
    }

    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equals("desc") ? Sort.by(new String[]{sortBy}).descending() : Sort.by(new String[]{sortBy}).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return this.contactRepo.findByUser(user, pageable);
    }

    public Page<Contact> searchByName(String name, int page, int size, String sortBy, String direction, User user) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(new String[]{sortBy}).descending() : Sort.by(new String[]{sortBy}).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return this.contactRepo.findByUserAndNameContainingIgnoreCase(user, name, pageable);
    }

    public Page<Contact> searchByEmail(String email, int page, int size, String sortBy, String direction, User user) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(new String[]{sortBy}).descending() : Sort.by(new String[]{sortBy}).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return this.contactRepo.findByUserAndEmailContainingIgnoreCase(user, email, pageable);
    }

    public Page<Contact> searchByphoneNumber(String phoneNumber, int page, int size, String sortBy, String direction, User user) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(new String[]{sortBy}).descending() : Sort.by(new String[]{sortBy}).ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        return this.contactRepo.findByUserAndPhoneNumberContaining(user, phoneNumber, pageable);
    }

    public List<Contact> getAllContactsByUser(User user) {
        return this.contactRepo.findByUser(user);
    }

    public List<Contact> getUpcomingBirthdays(User user) {
        List<Contact> allContacts = this.contactRepo.findByUser(user);
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30L);
        return (List)allContacts.stream().filter((contact) -> {
            if (contact.getBirthday() == null) {
                return false;
            } else {
                LocalDate bday = contact.getBirthday();
                LocalDate nextBday = bday.withYear(today.getYear());
                if (nextBday.isBefore(today)) {
                    nextBday = nextBday.plusYears(1L);
                }

                return !nextBday.isAfter(thirtyDaysLater) && !nextBday.isBefore(today);
            }
        }).collect(Collectors.toList());
    }

    public List<Contact> getRecentlyModifiedContacts(User user, int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(new String[]{"lastModifiedDate"}).descending());
        return this.contactRepo.findTopNByUserOrderByLastModifiedDateDesc(user, pageable);
    }
}
