package DAO;
import Model.Account;
import Util.ConnectionUtil;

import java.util.*;
import java.sql.*;

public class AccountDAO {
    // Retrieve all accounts from the "account" table
    public List<Account> getAllAccounts() {
        // Establish the connection to the database
        Connection connection = ConnectionUtil.getConnection();
        // Initialize an array list that will store all the accounts from the query on the database
        List<Account> accounts = new ArrayList<>();

        try {
            // Create SQL statement
            String sql = "SELECT * FROM account";

            // Create prepared statement to execute SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Create a result set to store the results of the SQL statement query
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                // Parse the SQL data into an object
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                                              rs.getString("password"));
                // Add the account to the accounts array list
                accounts.add(account);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return accounts;
    }

    // Registers a new account into the "account" table
    public Account registerAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            // Create SQL statement
            String sql = "INSERT INTO account (account_id, username, password) VALUES (?, ?, ?)";
            // Create prepared statement that will execute the SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Use prepared statement setters for paramters
            preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());

            // Execute prepared SQL statement
            preparedStatement.executeQuery();
            // Return account object 
            return account;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    // Retrieve an account by its username
    public Account getAccountByUsername(String username) {
        // Establish connection to database
        Connection connection = ConnectionUtil.getConnection();

        try {
            // Prepare SQL statement
            String sql = "SELECT * FROM account WHERE username = ?";

            // Write prepared statement to execute SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Use prepared statement setter to set parameter
            preparedStatement.setString(1, username);

            // Create result set to store the results of the SQL query
            ResultSet rs = preparedStatement.executeQuery();

            // Parse SQL data into an object
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                                              
                return account;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }
}
