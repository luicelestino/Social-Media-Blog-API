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

    // Use MessageDAO to create new message
    public Message addMessage(Message message) {
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

    // Use MessageDAO to update a message's text by its ID
    public boolean updateMessageByID(String message_text, int message_id) {
        return messageDAO.updateMessageByID(message_text, message_id);
    }

    // Use MessageDAO to retrieve all messages from an account's ID
    public List<Message> getAllMessagesByAccountID(int account_id) {
        return messageDAO.getAllMessagesByAccountID(account_id);
    } 

}
