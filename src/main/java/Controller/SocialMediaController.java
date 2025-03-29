package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
 



/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
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

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postRegisterHandler(Context context) throws JsonProcessingException{
        String jsonString = context.body();
        
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(jsonString, Account.class);

        if()

        context.json(account);
        
    }

    private void postLoginHandler(Context context) {
        context.json("sample text");
    }

    private void postMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void getMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void deleteMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void patchMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesByAccountIdHandler(Context context) {
        context.json("sample text");
    }


}