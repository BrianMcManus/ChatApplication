/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Megatronus
 */
public class Message implements Serializable{
    private int messageId;
    private String messageContent;
    private String receiver;
    private boolean read;
    private Date timeSent;
    private boolean inForum;

    public Message() {
       
    }

    public Message(String messageContent, String receiver, boolean read, Date timeSent, boolean inForum) {
        this.messageContent = messageContent;
        this.receiver = receiver;
        this.read = read;
        this.timeSent = timeSent;
        this.inForum = inForum;
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
        return receiver;
    }

    public void setSender(String receiver) {
        this.receiver = receiver;
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

    public boolean isInForum() {
        return inForum;
    }

    public void setInForum(boolean inForum) {
        this.inForum = inForum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.messageContent);
        hash = 23 * hash + Objects.hashCode(this.receiver);
        hash = 23 * hash + (this.read ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.timeSent);
        hash = 23 * hash + (this.inForum ? 1 : 0);
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
        if (this.read != other.read) {
            return false;
        }
        if (this.inForum != other.inForum) {
            return false;
        }
        if (!Objects.equals(this.messageContent, other.messageContent)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (!Objects.equals(this.timeSent, other.timeSent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", messageContent=" + messageContent + ", receiver=" + receiver + ", read=" + read + ", timeSent=" + timeSent + ", inForum=" + inForum + '}';
    }

    
    
    

    
    
    
}