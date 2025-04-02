package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import DAO.AccountDAO;
import DAO.MessageDAO;
import java.util.*;

 



/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    AccountDAO accountDAO;
    MessageDAO messageDAO;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
        this.accountDAO = new AccountDAO();
        this.messageDAO = new MessageDAO();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessagesHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountIdHandler);

        return app;
    }

    // Handler for post on /register
    private void postRegisterHandler(Context ctx) throws JsonProcessingException{
        // Storing user input as a JSON string
        String jsonString = ctx.body();
        
        // Initialzie object mapper to convert json string to an object
        ObjectMapper om = new ObjectMapper();
        // Create account object from user input
        Account account = om.readValue(jsonString, Account.class);
        Account addedAccount = accountService.registerAccount(account);

        if(addedAccount != null) {
            // ctx.json(om.writeValueAsString(addedAccount));
            ctx.json(addedAccount);
        } else {
            ctx.status(400);
        }
        
    }

    private void postLoginHandler(Context ctx) throws JsonProcessingException{
        // Store user input as JSON string
        String jsonString = ctx.body();

        // Initialize object mapper to convert json string to object
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(jsonString, Account.class);
        Account loginAccount = accountService.loginAccount(account);


        // if (accountDAO.getAccountByUsername(username) != null)
        if (loginAccount != null) {
            // ctx.json(om.writeValueAsString(loginAccount));
            ctx.json(loginAccount);
        } else {
            ctx.status(401);
        }
    }

    // Handler for post on /messages
    private void postMessagesHandler(Context ctx) throws JsonProcessingException {
        // Storing user input as a JSON string
        String jsonString = ctx.body();
        
        // Initialize object mapper to convert JSON string to an object
        ObjectMapper om = new ObjectMapper();
        
        // Create message object from user input
        Message message = om.readValue(jsonString, Message.class);
        
        // Call the service method to handle message creation and validation
        Message addedMessage = messageService.addMessage(message);
    
        // If message was successfully added, return the message in JSON format
        if (addedMessage != null) {
            ctx.json(addedMessage);
        } else {
            ctx.status(400);
        }
    }
    
    // Handler for get on /messages
    private void getAllMessagesHandler(Context ctx) {
        // Create array list to store all messages
        List<Message> messages = messageService.getAllMessages();
        // Return array list as json
        ctx.json(messages);
    }

    private void getMessageByIdHandler(Context ctx) {
        // Parse message_id from the user input
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));

        // Retrieve the message using the message ID
        Message message = messageService.getMessageByID(message_id);

        if (message != null) {
            ctx.json(message);
        } else {
            ctx.result("");
        }
    }

    private void deleteMessageByIdHandler(Context ctx) {
        // Parse message_id from the user input
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));

        // Retrieve the message ysing the message ID
        Message messageToDelete = messageService.getMessageByID(message_id);

        if (messageToDelete == null) {
            ctx.result("");
            return;
        }

        // Delete the message using the message id
        boolean isDeleted = messageService.deleteMessageByID(message_id);

        if (isDeleted) {
            ctx.json(messageToDelete);
        } else {
            ctx.result("");
        }
    }

    private void patchMessageByIdHandler(Context ctx) {
        // Parse message_id from the path parameter
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        String requestBody = ctx.body();
    
        // Parse the request body to extract the new message text
        JsonObject jsonBody = new JsonParser().parse(requestBody).getAsJsonObject();
        String newMessageText = jsonBody.get("message_text").getAsString();
    
        // Call the service method to handle the update logic
        Message updatedMessage = messageService.updateMessageByID(newMessageText, message_id);
    
        // Return updated message to user if successful
        if (updatedMessage != null) {
            ctx.json(updatedMessage);  
        } else {
            ctx.status(400);  
        }
    }

    private void getAllMessagesByAccountIdHandler(Context ctx) {
        // Parse account_id from user input
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));

        // Retrieve all messages from a given account id
        List<Message> messages = messageService.getAllMessagesByAccountID(account_id);

        if (messages != null) {
            ctx.json(messages);
        } else {
            ctx.result("");
        }
    }
}