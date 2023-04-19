package Loans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private Connection connection;

    public LoanDAO(Connection connection) {
        this.connection = connection;
    }

    public void addLoan(Loan loan) throws SQLException {
        String query = "INSERT INTO loan (account_id, amount, interest_rate, term_months) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, loan.getAccountId());
        statement.setDouble(2, loan.getPrincipalAmt());
        statement.setDouble(3, loan.getRateOfInterest());
        statement.setInt(4, loan.getTime());

        statement.executeUpdate();
        statement.close();
    }

    public void updateLoan(Loan loan) throws SQLException {
        String query = "UPDATE loan SET account_id=?, amount=?, interest_rate=?, term_months=? WHERE loan_id=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, loan.getAccountId());
        statement.setDouble(2, loan.getPrincipalAmt());
        statement.setDouble(3, loan.getPrincipalAmt());
        statement.setInt(4, loan.getTime());
        statement.setInt(5, loan.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteLoan(int loanId) throws SQLException {
        String query = "DELETE FROM loan WHERE loan_id=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, loanId);

        statement.executeUpdate();
        statement.close();
    }

    public Loan getLoanById(int loanId) throws SQLException {
        String query = "SELECT * FROM loan WHERE loan_id=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, loanId);

        ResultSet rs = statement.executeQuery();
        Loan loan = null;

        if (rs.next()) {
            int accountId = rs.getInt("account_id");
            double amount = rs.getDouble("amount");
            double interestRate = rs.getDouble("interest_rate");
            int termMonths = rs.getInt("term_months");

            loan = new Loan(loanId, accountId, amount, interestRate, termMonths);
        }

        rs.close();
        statement.close();
        return loan;
    }

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM loan");

            while (resultSet.next()) {
                Loan loan = new Loan();
                loan.setId(resultSet.getInt("id"));
                loan.setApplicationId(resultSet.getInt("application_id"));
                loan.setAccountId(resultSet.getInt("account_id"));
                loan.setRateOfInterest(resultSet.getDouble("rate_of_interest"));
                loan.setPrincipalAmt(resultSet.getDouble("principal_amt"));
                loan.setTime(resultSet.getInt("time"));

                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
}
