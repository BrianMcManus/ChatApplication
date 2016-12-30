package business;

/**
 *
 * @author Brian
 * 
 * This class is used to identify what attributes are present within a userMessage object
 * 
 */
public class UserMessage {
    //The id of the person recieving the message
    private int recipientId;
    //The id of the message sent 
    private int messageId;

    //Empty constructor
    public UserMessage() {
    }

    //Paramterized construtor using the recipient's id and the message id to create a new UserMesage object
    public UserMessage(int recipientId, int messageId) {
        this.recipientId = recipientId;
        this.messageId = messageId;
    }

    //Setters and Getters
    
    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    //Get hashcode of a UserMessage object
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.recipientId;
        hash = 37 * hash + this.messageId;
        return hash;
    }

    //Check equivalency of 2 UserMessage objects
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
        final UserMessage other = (UserMessage) obj;
        if (this.recipientId != other.recipientId) {
            return false;
        }
        if (this.messageId != other.messageId) {
            return false;
        }
        return true;
    }

    //Output userMessage attributes in the form of a string.
    @Override
    public String toString() {
        return "UserMessage{" + "recipientId=" + recipientId + ", messageId=" + messageId + '}';
    }
    
    
}
