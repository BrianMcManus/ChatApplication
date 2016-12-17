/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Megatronus
 */
public class Message {
    private int messageId;
    private String messageContent;
    private String sender;
    private String recipient;
    private boolean read;
    private Date timeSent;

    public Message() {
    }

    public Message(int messageId, String messageContent, String sender, String recipient, boolean read, Date timeSent) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.sender = sender;
        this.recipient = recipient;
        this.read = read;
        this.timeSent = timeSent;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.messageId;
        hash = 37 * hash + Objects.hashCode(this.messageContent);
        hash = 37 * hash + Objects.hashCode(this.sender);
        hash = 37 * hash + Objects.hashCode(this.recipient);
        hash = 37 * hash + (this.read ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.timeSent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.messageId != other.messageId) {
            return false;
        }
        if (this.read != other.read) {
            return false;
        }
        if (!Objects.equals(this.messageContent, other.messageContent)) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.recipient, other.recipient)) {
            return false;
        }
        if (!Objects.equals(this.timeSent, other.timeSent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", messageContent=" + messageContent + ", sender=" + sender + ", recipient=" + recipient + ", read=" + read + ", timeSent=" + timeSent + '}';
    }
    
    
}