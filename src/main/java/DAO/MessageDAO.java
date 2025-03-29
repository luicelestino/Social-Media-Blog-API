package DAO;

import Model.Message;

import Util.ConnectionUtil;
import java.sql.*;
import java.util.*;


public class MessageDAO {
    // Retrieve all messages from the "message" table
    public List<Message> getAllMessages() {
        // Establish the connection to the database
        Connection connection = ConnectionUtil.getConnection();
        // Initialize an array list that will store all the messages from the query on the database
        List<Message> messages = new ArrayList<>();

        try {
            // Create the SQL statement
            String sql = "SELECT * FROM message";

            // Create prepared statement that will execute the SQL statement 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Create the result set that will store the results of the SQL statement
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                // Parse the SQL data into an object
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),
                                              rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return messages;

    }

    // Creates a new message into the "message" table
    public Message createMessage(Message message) {
        // Establish the connection to the database
        Connection connection = ConnectionUtil.getConnection();

        try {
            // Create SQL statement
            String sql = "INSERT INTO message (message_id, posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?, ?)";
            // Create prepared statement that will execute the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Use prepared statement setters for parameters
            preparedStatement.setInt(1, message.getMessage_id());
            preparedStatement.setInt(2, message.getPosted_by());
            preparedStatement.setString(3, message.getMessage_text());
            preparedStatement.setLong(4, message.getTime_posted_epoch());

            // Execute prepared SQL statement 
            preparedStatement.executeQuery();
            // Return message object
            return message;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    // Retrieve a message by its ID
    public Message getMessageByID(int messageID) {
        // Establish connection to database
        Connection connection = ConnectionUtil.getConnection();

        try {
            // Prepare SQL statement
            String sql = "SELECT * FROM message WHERE message_id = ?";

            // Write prepared statement to execute SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Use prepared statement setter to set parameter
            preparedStatement.setInt(1, messageID);

            // Create result set to store the results of the SQL query
            ResultSet rs = preparedStatement.executeQuery();

            // Parse SQL data into an object
            while(rs.next()) {
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"),
                                              rs.getLong("time_posted_epoch"));
                return message;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    // Delete a message by its ID
    public boolean deleteMessageByID(int message_id) {
        // Establish connection to database
        Connection connection = ConnectionUtil.getConnection();

        try {
            // Prepare SQL statement
            String sql = "DELETE * FROM message WHERE message_id = ?";
            
            // Create prepared statement that will execute the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Use prepared statement setters to set parameter
            preparedStatement.setInt(1, message_id);

            // Execute the prepared statement
            // Save the number of rows affected by SQL query to determine if delete was successful or not i.e 0 rows vs 1 row
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } 
    }


    // Update a message's "message_txt" by its ID


    // Retrieve all messages written by a particular user identified by their "account_id"
}
