package com.flexon.bank;

import java.sql.*;
import java.util.ArrayList;

public class BankAccountsDAO {
    private final String url = "jdbc:mysql://mysql-db-uswest1.cmsymc235ooy.us-west-1.rds.amazonaws.com:3306/flexon_bank_db";
    private final String user = "admin";
    private final String password = "admin123";

    // Create
    public int createAccount(BankAccount account) {
        int accountNum = 0;
        ResultSet rs = null;

        String sql = "INSERT INTO accounts(balance, customer_name, email, phone_num) " +
                     "VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getCustomerName());
            pstmt.setString(3, account.getEmail());
            pstmt.setString(4, account.getPhoneNum());

            if (pstmt.executeUpdate() == 1) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    accountNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return accountNum;
    }

    // Read
    public ArrayList<BankAccount> getAllAccounts() {
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        String sql = "SELECT account_num, balance, customer_name, email, phone_num " +
                     "FROM accounts";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                bankAccounts.add(new BankAccount(rs.getInt("account_num"),
                        rs.getDouble("balance"),
                        rs.getString("customer_name"),
                        rs.getString("email"),
                        rs.getString("phone_num")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }

    public BankAccount getAccount(int accountNum) {
        BankAccount account = null;

        String sql = "SELECT account_num, balance, customer_name, email, phone_num " +
                     "FROM accounts " +
                     "WHERE account_num = " + accountNum;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                account = new BankAccount(rs.getInt("account_num"),
                        rs.getDouble("balance"),
                        rs.getString("customer_name"),
                        rs.getString("email"),
                        rs.getString("phone_num"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    // Update
    public void updateAccount(BankAccount account) {
        String sql = "UPDATE accounts " +
                     "SET balance = ?, customer_name = ?, email = ?, phone_num = ? " +
                     "WHERE account_num = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getCustomerName());
            pstmt.setString(3, account.getEmail());
            pstmt.setString(4, account.getPhoneNum());
            pstmt.setInt(5, account.getAccountNum());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete
    public boolean deleteAccount(int accountNum) {
        boolean isDeleted = false;

        String sql = "DELETE FROM accounts " +
                     "WHERE account_num = " + accountNum;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            if (stmt.executeUpdate(sql) > 0)
                isDeleted = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isDeleted;
    }
}
