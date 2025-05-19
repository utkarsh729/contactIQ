//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.services;

import com.scm.entities.Contact;
import com.scm.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ContactService {
    Contact saveContact(Contact contact);

    Contact updateContact(Contact contact);

    List<Contact> getAllContact();

    Contact getById(String id);

    void deleteContact(String id);

    List<Contact> searchContact(String name, String email, String phoneNumber);

    List<Contact> getByUserId(String userId);

    List<Contact> getAllContactsByUser(User user);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction);

    Page<Contact> searchByName(String name, int page, int size, String sortBy, String direction, User user);

    Page<Contact> searchByEmail(String email, int page, int size, String sortBy, String direction, User user);

    Page<Contact> searchByphoneNumber(String phoneNumber, int page, int size, String sortBy, String direction, User user);

    List<Contact> getUpcomingBirthdays(User user);

    List<Contact> getRecentlyModifiedContacts(User user, int limit);
}
