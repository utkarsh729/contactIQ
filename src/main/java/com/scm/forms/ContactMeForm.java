//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Generated;

public class ContactMeForm {
    private @NotBlank(
            message = "Email is required"
    ) @Email(
            message = "Invalid email format"
    ) String email;
    private @NotBlank(
            message = "Name is required"
    ) String name;
    private @NotBlank(
            message = "Message cannot be empty"
    ) String message;

    @Generated
    public static ContactMeFormBuilder builder() {
        return new ContactMeFormBuilder();
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getMessage() {
        return this.message;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setMessage(final String message) {
        this.message = message;
    }

    @Generated
    public ContactMeForm() {
    }

    @Generated
    public ContactMeForm(final String email, final String name, final String message) {
        this.email = email;
        this.name = name;
        this.message = message;
    }

    @Generated
    public static class ContactMeFormBuilder {
        @Generated
        private String email;
        @Generated
        private String name;
        @Generated
        private String message;

        @Generated
        ContactMeFormBuilder() {
        }

        @Generated
        public ContactMeFormBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @Generated
        public ContactMeFormBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Generated
        public ContactMeFormBuilder message(final String message) {
            this.message = message;
            return this;
        }

        @Generated
        public ContactMeForm build() {
            return new ContactMeForm(this.email, this.name, this.message);
        }

        @Generated
        public String toString() {
            return "ContactMeForm.ContactMeFormBuilder(email=" + this.email + ", name=" + this.name + ", message=" + this.message + ")";
        }
    }
}
