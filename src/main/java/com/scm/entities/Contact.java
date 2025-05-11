//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(
            length = 10000
    )
    private String description;
    private boolean favourite;
    private String cloudinaryImagePublicId;
    private String instaLink;
    private String linkedInLink;
    @ManyToOne
    @JsonIgnore
    private User user;
    private LocalDate birthday;
    @OneToMany(
            mappedBy = "contact",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<SocialLinks> links;
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @Generated
    private static boolean $default$favourite() {
        return false;
    }

    @Generated
    private static List<SocialLinks> $default$links() {
        return new ArrayList();
    }

    @Generated
    public static ContactBuilder builder() {
        return new ContactBuilder();
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public String getAddress() {
        return this.address;
    }

    @Generated
    public String getPicture() {
        return this.picture;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public boolean isFavourite() {
        return this.favourite;
    }

    @Generated
    public String getCloudinaryImagePublicId() {
        return this.cloudinaryImagePublicId;
    }

    @Generated
    public String getInstaLink() {
        return this.instaLink;
    }

    @Generated
    public String getLinkedInLink() {
        return this.linkedInLink;
    }

    @Generated
    public User getUser() {
        return this.user;
    }

    @Generated
    public LocalDate getBirthday() {
        return this.birthday;
    }

    @Generated
    public List<SocialLinks> getLinks() {
        return this.links;
    }

    @Generated
    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setAddress(final String address) {
        this.address = address;
    }

    @Generated
    public void setPicture(final String picture) {
        this.picture = picture;
    }

    @Generated
    public void setDescription(final String description) {
        this.description = description;
    }

    @Generated
    public void setFavourite(final boolean favourite) {
        this.favourite = favourite;
    }

    @Generated
    public void setCloudinaryImagePublicId(final String cloudinaryImagePublicId) {
        this.cloudinaryImagePublicId = cloudinaryImagePublicId;
    }

    @Generated
    public void setInstaLink(final String instaLink) {
        this.instaLink = instaLink;
    }

    @Generated
    public void setLinkedInLink(final String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    @JsonIgnore
    @Generated
    public void setUser(final User user) {
        this.user = user;
    }

    @Generated
    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    @Generated
    public void setLinks(final List<SocialLinks> links) {
        this.links = links;
    }

    @Generated
    public void setLastModifiedDate(final LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Generated
    public Contact() {
        this.favourite = $default$favourite();
        this.links = $default$links();
    }

    @Generated
    public Contact(final String id, final String name, final String email, final String phoneNumber, final String address, final String picture, final String description, final boolean favourite, final String cloudinaryImagePublicId, final String instaLink, final String linkedInLink, final User user, final LocalDate birthday, final List<SocialLinks> links, final LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.picture = picture;
        this.description = description;
        this.favourite = favourite;
        this.cloudinaryImagePublicId = cloudinaryImagePublicId;
        this.instaLink = instaLink;
        this.linkedInLink = linkedInLink;
        this.user = user;
        this.birthday = birthday;
        this.links = links;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Generated
    public static class ContactBuilder {
        @Generated
        private String id;
        @Generated
        private String name;
        @Generated
        private String email;
        @Generated
        private String phoneNumber;
        @Generated
        private String address;
        @Generated
        private String picture;
        @Generated
        private String description;
        @Generated
        private boolean favourite$set;
        @Generated
        private boolean favourite$value;
        @Generated
        private String cloudinaryImagePublicId;
        @Generated
        private String instaLink;
        @Generated
        private String linkedInLink;
        @Generated
        private User user;
        @Generated
        private LocalDate birthday;
        @Generated
        private boolean links$set;
        @Generated
        private List<SocialLinks> links$value;
        @Generated
        private LocalDateTime lastModifiedDate;

        @Generated
        ContactBuilder() {
        }

        @Generated
        public ContactBuilder id(final String id) {
            this.id = id;
            return this;
        }

        @Generated
        public ContactBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Generated
        public ContactBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @Generated
        public ContactBuilder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Generated
        public ContactBuilder address(final String address) {
            this.address = address;
            return this;
        }

        @Generated
        public ContactBuilder picture(final String picture) {
            this.picture = picture;
            return this;
        }

        @Generated
        public ContactBuilder description(final String description) {
            this.description = description;
            return this;
        }

        @Generated
        public ContactBuilder favourite(final boolean favourite) {
            this.favourite$value = favourite;
            this.favourite$set = true;
            return this;
        }

        @Generated
        public ContactBuilder cloudinaryImagePublicId(final String cloudinaryImagePublicId) {
            this.cloudinaryImagePublicId = cloudinaryImagePublicId;
            return this;
        }

        @Generated
        public ContactBuilder instaLink(final String instaLink) {
            this.instaLink = instaLink;
            return this;
        }

        @Generated
        public ContactBuilder linkedInLink(final String linkedInLink) {
            this.linkedInLink = linkedInLink;
            return this;
        }

        @JsonIgnore
        @Generated
        public ContactBuilder user(final User user) {
            this.user = user;
            return this;
        }

        @Generated
        public ContactBuilder birthday(final LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        @Generated
        public ContactBuilder links(final List<SocialLinks> links) {
            this.links$value = links;
            this.links$set = true;
            return this;
        }

        @Generated
        public ContactBuilder lastModifiedDate(final LocalDateTime lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        @Generated
        public Contact build() {
            boolean favourite$value = this.favourite$value;
            if (!this.favourite$set) {
                favourite$value = Contact.$default$favourite();
            }

            List<SocialLinks> links$value = this.links$value;
            if (!this.links$set) {
                links$value = Contact.$default$links();
            }

            return new Contact(this.id, this.name, this.email, this.phoneNumber, this.address, this.picture, this.description, favourite$value, this.cloudinaryImagePublicId, this.instaLink, this.linkedInLink, this.user, this.birthday, links$value, this.lastModifiedDate);
        }

        @Generated
        public String toString() {
            String var10000 = this.id;
            return "Contact.ContactBuilder(id=" + var10000 + ", name=" + this.name + ", email=" + this.email + ", phoneNumber=" + this.phoneNumber + ", address=" + this.address + ", picture=" + this.picture + ", description=" + this.description + ", favourite$value=" + this.favourite$value + ", cloudinaryImagePublicId=" + this.cloudinaryImagePublicId + ", instaLink=" + this.instaLink + ", linkedInLink=" + this.linkedInLink + ", user=" + String.valueOf(this.user) + ", birthday=" + String.valueOf(this.birthday) + ", links$value=" + String.valueOf(this.links$value) + ", lastModifiedDate=" + String.valueOf(this.lastModifiedDate) + ")";
        }
    }
}
