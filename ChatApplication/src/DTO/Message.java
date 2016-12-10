/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Megatronus
 */
public class Message {
    private int messageId;
    private String messageContent;

    public Message() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.messageId;
        hash = 29 * hash + Objects.hashCode(this.messageContent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
        if (!Objects.equals(this.messageContent, other.messageContent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", messageContent=" + messageContent + '}';
    }
    
}
