package main;

import java.util.HashMap;

import accounts.Account;
import operations.AccountOperations;
import operations.OperationFactory;
import utils.InputScanner;

public class InternetBankingApp {

    private static boolean successLogin;

    private static InputScanner input;
    private static HashMap<Integer, Account> accounts;
    private static Account userAccount;

    public static void main(String[] args) {

        accounts = new HashMap<Integer, Account>();
        successLogin = false;

        Account account1 = new Account(1, "password1", 1000.00);
        Account account2 = new Account(2, "password2", 2000.00);
        Account account3 = new Account(3, "password3", 3000.00);

        accounts.put(account1.getAccountNo(), account1);
        accounts.put(account2.getAccountNo(), account2);
        accounts.put(account3.getAccountNo(), account3);

        login();

        if(successLogin) {
            performOperation();
        }

    }

    private static void login() {
        int loginTries = 0;

        input = InputScanner.getInstance();

        System.out.println("Enter Account no.: ");      
        String userInput = input.readInput();

        System.out.println("Searching for account no. " + userInput);
        userAccount = accounts.get(Integer.parseInt(userInput));

        if(userAccount == null) {
            System.out.println("Account no. is invalid!");
        } else {
            while(true) {
                System.out.println("Please enter password: ");
                userInput = input.readInput();

                if(userAccount.getPassword().equals(userInput)) {
                    System.out.println("Login was performed successfully!");
                    successLogin = true;
                    break;
                } else {
                    System.out.println("Invalid password, please try again!");
                }

                loginTries ++;
                if(loginTries == 3) {
                    System.out.println("Maximum number of tries reached!");
                    break;
                }
            }
        }
    }

    private static void performOperation() {
        OperationFactory factory = new OperationFactory();

        boolean stop = false;

        while(!stop) {
            System.out.println("Please select one of the following posibilities: ");
            System.out.println("1. Check Account Summary");
            System.out.println("2. Deposit money in account");

            System.out.println("Your choice: ");
            String userInput = input.readInput();

            AccountOperations operation = factory.getOperation(userInput);
            operation.performOperation(userAccount);

            while(true) {
                System.out.println("Do you wish to make another operation? (Y/N)");
                userInput = input.readInput();

                if(userInput.equalsIgnoreCase("n")) {
                    stop = true;
                    break;
                } else if(userInput.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

}