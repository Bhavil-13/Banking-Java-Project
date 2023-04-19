package LoanApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoanApplicationDAO {

    Connection dbConnection;

    public LoanApplicationDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    public LoanApplication getLoanApplication(int applicationId) {
        LoanApplication loanApp = null;
        String sql = "SELECT * FROM loan_application WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, applicationId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                loanApp = new LoanApplication();
                loanApp.setId(rs.getInt("id"));
                loanApp.setAccountId(rs.getInt("account_id"));
                loanApp.setApplicantId(rs.getInt("applicant_id"));
                loanApp.setAmount(rs.getDouble("amount"));
                loanApp.setReason(rs.getString("reason"));
                loanApp.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return loanApp;
    }

    public LoanApplication createLoanApplication(int accountId, int applicantId, double amount, String reason) {
        LoanApplication loanApp = null;
        String sql = "INSERT INTO loan_applications(account_id, applicant_id, amount, reason, status) VALUES (?, ?, ?, ?, 'pending')";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, applicantId);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, reason);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    loanApp = new LoanApplication();
                    loanApp.setId(id);
                    loanApp.setAccountId(accountId);
                    loanApp.setApplicantId(applicantId);
                    loanApp.setAmount(amount);
                    loanApp.setReason(reason);
                    loanApp.setStatus("pending");
                }
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return loanApp;
    }
}
