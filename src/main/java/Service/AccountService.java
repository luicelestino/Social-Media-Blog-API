package Service;
import DAO.AccountDAO;
import Model.Account;

import java.util.*;

public class AccountService {
    // Declare AccountDAO so that the service file can use the AccountDAO methods
    // This is because the service layer will implement business logic using the DAO methods
    public AccountDAO accountDAO;

    // Constructor for no-args 
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    // Constructor for when AccountDAO is provided
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    // Use AccountDAO to retrieve all accounts
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    // Use AccountDAO to register an account
    // Implement business logic here
    // Account can only be registered if username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist
    public Account registerAccount(Account account) {
        // If username is blank invalidate registration
        if (account.getUsername().isBlank()) {
            return null;
        }
        // If account with that username already exists, invalidate registration
        if (accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }
        // If password for account is less than 4 characters, invalidate registration
        if (account.getPassword().length() < 4) {
            return null;
        }

        return accountDAO.registerAccount(account);
    }
}
    
