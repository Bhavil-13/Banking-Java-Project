package LoanApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public boolean updateLoanApplication(int loanAppId, String status) {
        boolean success = false;
        String sql = "UPDATE loan_applications SET status=? WHERE id=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, loanAppId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return success;
    }
    public List<LoanAppWithExtras> getAllLoansAppWithExtras() {
        List<LoanAppWithExtras> loansWithPersonAndBranch = new ArrayList<>();
        try {
            String sql = "SELECT loan_applications.application_id, loan_applications.amount, credit_score.score" +
            "FROM loan " +
            "INNER JOIN person ON loan_applications.person_id= person.person_id " +
            "INNER JOIN credit_score ON person.person_id = credit_score.person_id";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LoanAppWithExtras loanWithExtras = new LoanAppWithExtras();
                loanWithExtras.setAmount(resultSet.getDouble("principal_amt"));
                loanWithExtras.setApplicationId(resultSet.getInt("application_id"));
                loanWithExtras.setAccountId(resultSet.getInt("person_id"));
                loanWithExtras.setCreditScore(resultSet.getInt("score"));
                loansWithPersonAndBranch.add(loanWithExtras);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loansWithPersonAndBranch;
    }

}
