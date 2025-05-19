//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.forms;

import com.scm.validators.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Generated;

public class UserForm {
    private @NotBlank(
            message = "Name is required"
    ) @Size(
            min = 3,
            message = "Minimum 3 characters are required"
    ) String name;
    @UniqueEmail
    private @Email(
            message = "Invalid Email Address"
    ) @NotBlank(
            message = "email is required"
    ) String email;
    private @NotBlank(
            message = "password is required"
    ) @Size(
            min = 6,
            message = "password must be of length more then 6"
    ) String password;
    private @NotBlank(
            message = "about is blank"
    ) String about;
    private @Size(
            min = 8,
            max = 12,
            message = "Invalid phone NUmber"
    ) String phoneNumber;
    private String otp;

    @Generated
    public static UserFormBuilder builder() {
        return new UserFormBuilder();
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
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getAbout() {
        return this.about;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public String getOtp() {
        return this.otp;
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
    public void setPassword(final String password) {
        this.password = password;
    }

    @Generated
    public void setAbout(final String about) {
        this.about = about;
    }

    @Generated
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setOtp(final String otp) {
        this.otp = otp;
    }

    @Generated
    public UserForm(final String name, final String email, final String password, final String about, final String phoneNumber, final String otp) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.phoneNumber = phoneNumber;
        this.otp = otp;
    }

    @Generated
    public UserForm() {
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "UserForm(name=" + var10000 + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", about=" + this.getAbout() + ", phoneNumber=" + this.getPhoneNumber() + ", otp=" + this.getOtp() + ")";
    }

    @Generated
    public static class UserFormBuilder {
        @Generated
        private String name;
        @Generated
        private String email;
        @Generated
        private String password;
        @Generated
        private String about;
        @Generated
        private String phoneNumber;
        @Generated
        private String otp;

        @Generated
        UserFormBuilder() {
        }

        @Generated
        public UserFormBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Generated
        public UserFormBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @Generated
        public UserFormBuilder password(final String password) {
            this.password = password;
            return this;
        }

        @Generated
        public UserFormBuilder about(final String about) {
            this.about = about;
            return this;
        }

        @Generated
        public UserFormBuilder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Generated
        public UserFormBuilder otp(final String otp) {
            this.otp = otp;
            return this;
        }

        @Generated
        public UserForm build() {
            return new UserForm(this.name, this.email, this.password, this.about, this.phoneNumber, this.otp);
        }

        @Generated
        public String toString() {
            return "UserForm.UserFormBuilder(name=" + this.name + ", email=" + this.email + ", password=" + this.password + ", about=" + this.about + ", phoneNumber=" + this.phoneNumber + ", otp=" + this.otp + ")";
        }
    }
}
