//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.forms;

import com.scm.validators.ValidFile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Generated;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class ContactForm {
    private @NotBlank(
            message = "Name is required"
    ) String name;
    private @NotBlank(
            message = "Email is required"
    ) @Email(
            message = "Invalid email"
    ) String email;
    private @NotBlank(
            message = "Phone Number is required"
    ) @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Invalid Phone Number"
    ) String phoneNumber;
    private @NotBlank(
            message = "Address is required"
    ) String address;
    private String description;
    private String instaLink;
    private String linkedInLink;
    private boolean favourite;
    @ValidFile
    private MultipartFile contactImage;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private @Past(
            message = "Birthday must be past date"
    ) LocalDate birthday;
    private String picture;

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
    public String getDescription() {
        return this.description;
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
    public boolean isFavourite() {
        return this.favourite;
    }

    @Generated
    public MultipartFile getContactImage() {
        return this.contactImage;
    }

    @Generated
    public LocalDate getBirthday() {
        return this.birthday;
    }

    @Generated
    public String getPicture() {
        return this.picture;
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
    public void setDescription(final String description) {
        this.description = description;
    }

    @Generated
    public void setInstaLink(final String instaLink) {
        this.instaLink = instaLink;
    }

    @Generated
    public void setLinkedInLink(final String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    @Generated
    public void setFavourite(final boolean favourite) {
        this.favourite = favourite;
    }

    @Generated
    public void setContactImage(final MultipartFile contactImage) {
        this.contactImage = contactImage;
    }

    @Generated
    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    @Generated
    public void setPicture(final String picture) {
        this.picture = picture;
    }

    @Generated
    public ContactForm() {
    }

    @Generated
    public ContactForm(final String name, final String email, final String phoneNumber, final String address, final String description, final String instaLink, final String linkedInLink, final boolean favourite, final MultipartFile contactImage, final LocalDate birthday, final String picture) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.description = description;
        this.instaLink = instaLink;
        this.linkedInLink = linkedInLink;
        this.favourite = favourite;
        this.contactImage = contactImage;
        this.birthday = birthday;
        this.picture = picture;
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "ContactForm(name=" + var10000 + ", email=" + this.getEmail() + ", phoneNumber=" + this.getPhoneNumber() + ", address=" + this.getAddress() + ", description=" + this.getDescription() + ", instaLink=" + this.getInstaLink() + ", linkedInLink=" + this.getLinkedInLink() + ", favourite=" + this.isFavourite() + ", contactImage=" + String.valueOf(this.getContactImage()) + ", birthday=" + String.valueOf(this.getBirthday()) + ", picture=" + this.getPicture() + ")";
    }
}
