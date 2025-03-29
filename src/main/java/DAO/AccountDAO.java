package DAO;
import Model.Account;
import Util.ConnectionUtil;

import java.util.*;
import java.sql.*;

public class AccountDAO {
    // Retrieve all accounts from the "account" table
    public List<Account> getAllAccounts() {
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();

        try {
            String sql = "SELECT * FROM account";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                                              rs.getString("password"));
                accounts.add(account);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return accounts;
    }

    // Insert a new account into the "account" table
    public Account inserAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account (account_id, username, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());

            preparedStatement.executeQuery();
            return account;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }

    
}
