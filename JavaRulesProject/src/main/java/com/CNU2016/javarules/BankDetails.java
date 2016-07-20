package com.CNU2016.javarules;

/**
 * Created by mohanakumar on 20/07/16.
 */

/**
 * squid :S2226
 * Class being accessed by multiple threads
 */
public class BankDetails {

    private String username;

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private int accountBalance;

    public BankDetails()
    {

    }
}
