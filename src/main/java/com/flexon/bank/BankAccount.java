package com.flexon.bank;

public class BankAccount {

    private int accountNum;
    private double balance;
    private String customerName;
    private String email;
    private String phoneNum;

    public BankAccount(int accountNum, double balance, String customerName, String email, String phoneNum) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.customerName = customerName.trim().toUpperCase();
        this.email = email.toUpperCase();
        this.phoneNum = phoneNum;
    }

    public BankAccount(double balance, String customerName, String email, String phoneNum) {
        this.accountNum = 0;
        this.balance = balance;
        this.customerName = customerName.trim().toUpperCase();
        this.email = email.toUpperCase();
        this.phoneNum = phoneNum;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName.trim().toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Name: " + customerName +
                "\nAccount Number: " + accountNum +
                "\nBalance: $" + balance +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNum;
    }

    public void deposit(double amt) {
        balance += amt;
    }

    public boolean withdraw(double amt) {
        if (amt > balance)
            return false;
        else {
            balance -= amt;
            return true;
        }
    }

    public void printBalance() {
        System.out.println("Bank account for: " + customerName);
        System.out.println("Current balance: $" + balance);
    }

    public void printAccount() {
        System.out.println(this.toString());
    }
}
