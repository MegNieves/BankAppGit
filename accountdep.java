package operations;

import accounts.Account;
import utils.InputScanner;

public class AccountDeposit implements AccountOperations {

    @Override
    public void performOperation(Account account) {
        double balance = account.getBalance();

        System.out.println("Current balance: " + balance);
        System.out.println("Please insert the sum of money you wish to deposit in account: ");

        balance = balance + Double.parseDouble(InputScanner.getInstance().readInput());

        account.setBalance(balance);

        System.out.println("New account balance: " + account.getBalance());
    }

}