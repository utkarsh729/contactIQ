//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
        name = "users"
)
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(
            name = "user_name",
            nullable = false
    )
    private String name;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private String password;
    @Lob
    @Column(
            columnDefinition = "TEXT"
    )
    private String about;
    @Lob
    @Column(
            columnDefinition = "TEXT"
    )
    private String profilePic;
    private String phoneNumber;
    private boolean enabled;
    private boolean emailVerified;
    private boolean phoneVerified;
    @Enumerated(EnumType.STRING)
    private Providers provider;
    private String providerId;
    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Contact> contacts;
    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<String> rolesList;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = (Collection)this.rolesList.stream().map((role) -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    public String getUsername() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Generated
    private static boolean $default$enabled() {
        return false;
    }

    @Generated
    private static boolean $default$phoneVerified() {
        return false;
    }

    @Generated
    private static Providers $default$provider() {
        return Providers.SELF;
    }

    @Generated
    private static List<Contact> $default$contacts() {
        return new ArrayList();
    }

    @Generated
    private static List<String> $default$rolesList() {
        return new ArrayList();
    }

    @Generated
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Generated
    public String getUserId() {
        return this.userId;
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
    public String getAbout() {
        return this.about;
    }

    @Generated
    public String getProfilePic() {
        return this.profilePic;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public boolean isEmailVerified() {
        return this.emailVerified;
    }

    @Generated
    public boolean isPhoneVerified() {
        return this.phoneVerified;
    }

    @Generated
    public Providers getProvider() {
        return this.provider;
    }

    @Generated
    public String getProviderId() {
        return this.providerId;
    }

    @Generated
    public List<Contact> getContacts() {
        return this.contacts;
    }

    @Generated
    public List<String> getRolesList() {
        return this.rolesList;
    }

    @Generated
    public void setUserId(final String userId) {
        this.userId = userId;
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
    public void setProfilePic(final String profilePic) {
        this.profilePic = profilePic;
    }

    @Generated
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    @Generated
    public void setEmailVerified(final boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Generated
    public void setPhoneVerified(final boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    @Generated
    public void setProvider(final Providers provider) {
        this.provider = provider;
    }

    @Generated
    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }

    @Generated
    public void setContacts(final List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Generated
    public void setRolesList(final List<String> rolesList) {
        this.rolesList = rolesList;
    }

    @Generated
    public User(final String userId, final String name, final String email, final String password, final String about, final String profilePic, final String phoneNumber, final boolean enabled, final boolean emailVerified, final boolean phoneVerified, final Providers provider, final String providerId, final List<Contact> contacts, final List<String> rolesList) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.profilePic = profilePic;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.provider = provider;
        this.providerId = providerId;
        this.contacts = contacts;
        this.rolesList = rolesList;
    }

    @Generated
    public User() {
        this.enabled = $default$enabled();
        this.phoneVerified = $default$phoneVerified();
        this.provider = $default$provider();
        this.contacts = $default$contacts();
        this.rolesList = $default$rolesList();
    }

    @Generated
    public static class UserBuilder {
        @Generated
        private String userId;
        @Generated
        private String name;
        @Generated
        private String email;
        @Generated
        private String password;
        @Generated
        private String about;
        @Generated
        private String profilePic;
        @Generated
        private String phoneNumber;
        @Generated
        private boolean enabled$set;
        @Generated
        private boolean enabled$value;
        @Generated
        private boolean emailVerified;
        @Generated
        private boolean phoneVerified$set;
        @Generated
        private boolean phoneVerified$value;
        @Generated
        private boolean provider$set;
        @Generated
        private Providers provider$value;
        @Generated
        private String providerId;
        @Generated
        private boolean contacts$set;
        @Generated
        private List<Contact> contacts$value;
        @Generated
        private boolean rolesList$set;
        @Generated
        private List<String> rolesList$value;

        @Generated
        UserBuilder() {
        }

        @Generated
        public UserBuilder userId(final String userId) {
            this.userId = userId;
            return this;
        }

        @Generated
        public UserBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Generated
        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @Generated
        public UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        @Generated
        public UserBuilder about(final String about) {
            this.about = about;
            return this;
        }

        @Generated
        public UserBuilder profilePic(final String profilePic) {
            this.profilePic = profilePic;
            return this;
        }

        @Generated
        public UserBuilder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Generated
        public UserBuilder enabled(final boolean enabled) {
            this.enabled$value = enabled;
            this.enabled$set = true;
            return this;
        }

        @Generated
        public UserBuilder emailVerified(final boolean emailVerified) {
            this.emailVerified = emailVerified;
            return this;
        }

        @Generated
        public UserBuilder phoneVerified(final boolean phoneVerified) {
            this.phoneVerified$value = phoneVerified;
            this.phoneVerified$set = true;
            return this;
        }

        @Generated
        public UserBuilder provider(final Providers provider) {
            this.provider$value = provider;
            this.provider$set = true;
            return this;
        }

        @Generated
        public UserBuilder providerId(final String providerId) {
            this.providerId = providerId;
            return this;
        }

        @Generated
        public UserBuilder contacts(final List<Contact> contacts) {
            this.contacts$value = contacts;
            this.contacts$set = true;
            return this;
        }

        @Generated
        public UserBuilder rolesList(final List<String> rolesList) {
            this.rolesList$value = rolesList;
            this.rolesList$set = true;
            return this;
        }

        @Generated
        public User build() {
            boolean enabled$value = this.enabled$value;
            if (!this.enabled$set) {
                enabled$value = User.$default$enabled();
            }

            boolean phoneVerified$value = this.phoneVerified$value;
            if (!this.phoneVerified$set) {
                phoneVerified$value = User.$default$phoneVerified();
            }

            Providers provider$value = this.provider$value;
            if (!this.provider$set) {
                provider$value = User.$default$provider();
            }

            List<Contact> contacts$value = this.contacts$value;
            if (!this.contacts$set) {
                contacts$value = User.$default$contacts();
            }

            List<String> rolesList$value = this.rolesList$value;
            if (!this.rolesList$set) {
                rolesList$value = User.$default$rolesList();
            }

            return new User(this.userId, this.name, this.email, this.password, this.about, this.profilePic, this.phoneNumber, enabled$value, this.emailVerified, phoneVerified$value, provider$value, this.providerId, contacts$value, rolesList$value);
        }

        @Generated
        public String toString() {
            String var10000 = this.userId;
            return "User.UserBuilder(userId=" + var10000 + ", name=" + this.name + ", email=" + this.email + ", password=" + this.password + ", about=" + this.about + ", profilePic=" + this.profilePic + ", phoneNumber=" + this.phoneNumber + ", enabled$value=" + this.enabled$value + ", emailVerified=" + this.emailVerified + ", phoneVerified$value=" + this.phoneVerified$value + ", provider$value=" + String.valueOf(this.provider$value) + ", providerId=" + this.providerId + ", contacts$value=" + String.valueOf(this.contacts$value) + ", rolesList$value=" + String.valueOf(this.rolesList$value) + ")";
        }
    }
}
