//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.scm.helpers;

import lombok.Generated;

public class Message {
    private String content;
    private MessageTypes type;

    @Generated
    private static MessageTypes $default$type() {
        return MessageTypes.blue;
    }

    @Generated
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    @Generated
    public String getContent() {
        return this.content;
    }

    @Generated
    public MessageTypes getType() {
        return this.type;
    }

    @Generated
    public void setContent(final String content) {
        this.content = content;
    }

    @Generated
    public void setType(final MessageTypes type) {
        this.type = type;
    }

    @Generated
    public Message(final String content, final MessageTypes type) {
        this.content = content;
        this.type = type;
    }

    @Generated
    public Message() {
        this.type = $default$type();
    }

    @Generated
    public static class MessageBuilder {
        @Generated
        private String content;
        @Generated
        private boolean type$set;
        @Generated
        private MessageTypes type$value;

        @Generated
        MessageBuilder() {
        }

        @Generated
        public MessageBuilder content(final String content) {
            this.content = content;
            return this;
        }

        @Generated
        public MessageBuilder type(final MessageTypes type) {
            this.type$value = type;
            this.type$set = true;
            return this;
        }

        @Generated
        public Message build() {
            MessageTypes type$value = this.type$value;
            if (!this.type$set) {
                type$value = Message.$default$type();
            }

            return new Message(this.content, type$value);
        }

        @Generated
        public String toString() {
            String var10000 = this.content;
            return "Message.MessageBuilder(content=" + var10000 + ", type$value=" + String.valueOf(this.type$value) + ")";
        }
    }
}
