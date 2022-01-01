package operations;

import accounts.Account;

public class AccountSummary implements AccountOperations {

    @Override
    public void performOperation(Account account) {
        System.out.println("Account current balance: " + account.getBalance());
    }

}