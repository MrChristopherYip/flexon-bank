package com.flexon.bank;

import java.util.Scanner;

public class Bank {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        BankAccountsDAO bankAccounts = new BankAccountsDAO();

        while (choice != 5) {
            System.out.println("\nMain Menu");
            System.out.println("1. Open a new account");
            System.out.println("2. Print all accounts");
            System.out.println("3. Access an account");
            System.out.println("4. Close an account");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAccount Creation");
                    System.out.println("Account number: " + bankAccounts.createAccount(getAccountDetails()));
                    break;
                case 2:
                    System.out.println();
                    for (BankAccount account : bankAccounts.getAllAccounts()) {
                        account.printAccount();
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.print("\nEnter account number to access: ");
                    BankAccount account = bankAccounts.getAccount(input.nextInt());
                    input.nextLine();

                    if (account == null)
                        System.out.println("\nAccount does not exist!");
                    else
                        bankAccounts.updateAccount(accessAccount(account));
                    break;
                case 4:
                    System.out.print("\nEnter account number to close: ");
                    if (!bankAccounts.deleteAccount(input.nextInt()))
                        System.out.println("\nAccount does not exist!");
                    input.nextLine();
                    break;
                case 5:
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nSelection invalid!");
            }
        }
        input.close();
    }

    private static BankAccount getAccountDetails() {

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Balance: ");
        double balance = input.nextDouble();
        input.nextLine();

        System.out.print("Email: ");
        String email = input.next();
        input.nextLine();

        System.out.print("Phone number: ");
        String phoneNum = input.next();
        input.nextLine();

        return new BankAccount(balance, name, email, phoneNum);
    }

    private static BankAccount accessAccount(BankAccount account) {
        int choice = 0;

        while (choice != 5) {
            System.out.println("\nAccount Menu");
            System.out.println("1. View account balance");
            System.out.println("2. Make a deposit");
            System.out.println("3. Make a withdrawal");
            System.out.println("4. Update account information");
            System.out.println("5. Exit to main menu");
            System.out.print("Choose an option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println();
                    account.printBalance();
                    break;
                case 2:
                    System.out.print("\nEnter amount to deposit: $");
                    account.deposit(input.nextDouble());
                    input.nextLine();
                    break;
                case 3:
                    System.out.print("\nEnter amount to withdraw: $");
                    if (!account.withdraw(input.nextDouble()))
                        System.out.println("\nInsufficient funds!");
                    input.nextLine();
                    break;
                case 4:
                    changeAccountDetails(account);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nSelection invalid!");
            }
        }
        return account;
    }

    private static void changeAccountDetails(BankAccount account) {

        System.out.println("Name: " + account.getCustomerName());
        System.out.print("New name (leave blank to skip change): ");
        String s = input.nextLine().trim();
        if (s.length() > 0)
            account.setCustomerName(s);

        System.out.println("Email: " + account.getEmail());
        System.out.print("New email (leave blank to skip change): ");
        s = input.nextLine().trim();
        if (s.length() > 0)
            account.setEmail(s);

        System.out.println("Phone number: " + account.getPhoneNum());
        System.out.print("New number (leave blank to skip change): ");
        s = input.nextLine().trim();
        if (s.length() > 0)
            account.setPhoneNum(s);
    }
}