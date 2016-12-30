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
 * 
 * This class is used to define what attributes a 'Message' object contains,
 * it implements Serializable in order to allow the object to be passed over sockets
 */
public class Message implements Serializable{
    //The id used to identify the message
    private int messageId;
    //The message to be sent
    private String messageContent;
    //The receiver of the message
    private String receiver;
    //States if the message has been read or not
    private boolean read;
    //States the time the message was sent
    private Date timeSent;
    //States if the message was sent publicly or privately
    private boolean inForum;

    //Empty constructor
    public Message() {
       
    }

    //Parameterized constructer without id for message obects that have not been assigned an id yet
    public Message(String messageContent, String receiver, boolean read, Date timeSent, boolean inForum) {
        this.messageContent = messageContent;
        this.receiver = receiver;
        this.read = read;
        this.timeSent = timeSent;
        this.inForum = inForum;
    }
    
    //Parameterized constructer with id for message obects that have been assigned an id by the database
    public Message(int messageId, String messageContent, String receiver, boolean read, Date timeSent, boolean inForum) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.receiver = receiver;
        this.read = read;
        this.timeSent = timeSent;
        this.inForum = inForum;
    }

    //Setters and Getters
    
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
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

    //Get hashcode of object
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.messageId;
        hash = 23 * hash + Objects.hashCode(this.messageContent);
        hash = 23 * hash + Objects.hashCode(this.receiver);
        hash = 23 * hash + (this.read ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.timeSent);
        hash = 23 * hash + (this.inForum ? 1 : 0);
        return hash;
    }

    //Verify the equilivancy of two message objects
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

    //Output the attributes of the message object to string values
    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", messageContent=" + messageContent + ", receiver=" + receiver + ", read=" + read + ", timeSent=" + timeSent + ", inForum=" + inForum + '}';
    }

    
    
    

    
    
    
}