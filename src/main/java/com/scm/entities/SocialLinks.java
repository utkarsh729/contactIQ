//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Generated;

@Entity
public class SocialLinks {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String link;
    private String title;
    @ManyToOne
    private Contact contact;

    @Generated
    public static SocialLinksBuilder builder() {
        return new SocialLinksBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getLink() {
        return this.link;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public Contact getContact() {
        return this.contact;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setLink(final String link) {
        this.link = link;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setContact(final Contact contact) {
        this.contact = contact;
    }

    @Generated
    public SocialLinks() {
    }

    @Generated
    public SocialLinks(final Long id, final String link, final String title, final Contact contact) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.contact = contact;
    }

    @Generated
    public static class SocialLinksBuilder {
        @Generated
        private Long id;
        @Generated
        private String link;
        @Generated
        private String title;
        @Generated
        private Contact contact;

        @Generated
        SocialLinksBuilder() {
        }

        @Generated
        public SocialLinksBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public SocialLinksBuilder link(final String link) {
            this.link = link;
            return this;
        }

        @Generated
        public SocialLinksBuilder title(final String title) {
            this.title = title;
            return this;
        }

        @Generated
        public SocialLinksBuilder contact(final Contact contact) {
            this.contact = contact;
            return this;
        }

        @Generated
        public SocialLinks build() {
            return new SocialLinks(this.id, this.link, this.title, this.contact);
        }

        @Generated
        public String toString() {
            Long var10000 = this.id;
            return "SocialLinks.SocialLinksBuilder(id=" + var10000 + ", link=" + this.link + ", title=" + this.title + ", contact=" + String.valueOf(this.contact) + ")";
        }
    }
}
