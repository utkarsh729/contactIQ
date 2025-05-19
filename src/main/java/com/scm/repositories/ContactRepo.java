//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.repositories;

import com.scm.entities.Contact;
import com.scm.entities.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {
    @Query("Select c from Contact c where c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

    Page<Contact> findByUser(User user, Pageable pageable);

    Page<Contact> findByUserAndNameContainingIgnoreCase(User user, String name, Pageable pageable);

    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneNumber, Pageable pageable);

    Page<Contact> findByUserAndEmailContainingIgnoreCase(User user, String email, Pageable pageable);

    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user = :user AND c.birthday BETWEEN :start AND :end")
    List<Contact> findByUserAndBirthdayBetween(@Param("user") User user, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT c FROM Contact c WHERE c.user = :user ORDER BY c.lastModifiedDate DESC")
    List<Contact> findTopNByUserOrderByLastModifiedDateDesc(@Param("user") User user, Pageable pageable);
}
