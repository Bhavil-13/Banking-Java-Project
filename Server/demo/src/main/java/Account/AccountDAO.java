package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    Connection dbConnection;

    public AccountDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    public int addAccount(int person_id, double initial_balance) {
        String sql = "INSERT INTO account(person_id, balance) VALUES(?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, person_id);
            pstmt.setDouble(2, initial_balance);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    return (generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return -1;
    }

    public List<Account> getAccountsByPersonId(int person_id) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT id, person_id, balance FROM account WHERE person_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int account_id = rs.getInt("id");
                double balance = rs.getDouble("balance");
                System.out.println(account_id+"  "+balance);
                Account account = new Account(person_id, balance);
                account.setId(account_id);
                accounts.add(account);
            }
            return accounts;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}
