package Service;
import DAO.MessageDAO;
import Model.Message;

import java.util.*;

public class MessageService {
    // Declare MessageDAO so that the service file can use the MessageDAO methods
    // This is because the service layer will implement the business logic
    public MessageDAO messageDAO;

    // Constructor for no-args
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    // Constructor for when MessageDAO is provided
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message) {
        // Check if message_text is blank or more than 255 characters
        if (message.getMessage_text() == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255) {
            return null; // Return null to indicate validation failure
        }
        
        // If validation passes, create the message via the DAO
        return messageDAO.createMessage(message);
    }

    // Use MessageDAO to retrieve all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    // Use MessageDAO to retrieve messages by its ID
    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    // Use MessageDAO to delete a message by its ID 
    public boolean deleteMessageByID(int message_id) {
        return messageDAO.deleteMessageByID(message_id);
    }

    // Changed return type to Message so that the method returns the whole message object on update
    // Prior logic would return the existing message after it was updated to show the changes
    // Old approach was clunky so this was changed
    public Message updateMessageByID(String newMessageText, int message_id) {
        // Validate the new message text
        if (newMessageText == null || newMessageText.isBlank() || newMessageText.length() > 255) {
            return null;  
        }
    
        // Check if the message exists
        Message existingMessage = messageDAO.getMessageByID(message_id);
        if (existingMessage == null) {
            return null;  
        }
    
        // Proceed to update the message via the DAO
        boolean updateSuccess = messageDAO.updateMessageByID(newMessageText, message_id);
        if (updateSuccess) {
            // If the update is successful, fetch the updated message and return it
            return messageDAO.getMessageByID(message_id);
        }
    
        // If the update failed, return null
        return null;
    }

    // Use MessageDAO to retrieve all messages from an account's ID
    public List<Message> getAllMessagesByAccountID(int account_id) {
        return messageDAO.getAllMessagesByAccountID(account_id);
    } 

}
