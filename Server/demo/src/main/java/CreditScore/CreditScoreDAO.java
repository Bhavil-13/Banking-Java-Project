package CreditScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditScoreDAO {
    private final Connection connection;
    public CreditScoreDAO(Connection connection) {
        this.connection = connection;
    }
    public CreditScore findById(int id) throws SQLException {
        CreditScore creditScore = null;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit_scores WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            creditScore = new CreditScore();
            creditScore.setId(resultSet.getInt("id"));
            creditScore.setScore(resultSet.getInt("score"));
            creditScore.setSalary(resultSet.getDouble("salary"));
            creditScore.setNetWorth(resultSet.getDouble("net_worth"));
            creditScore.setTotalLoans(resultSet.getDouble("total_loans"));
            creditScore.setRepaidLoans(resultSet.getDouble("repaid_loans"));
            creditScore.setOccupation(resultSet.getString("occupation"));
        }
        resultSet.close();
        statement.close();
        return creditScore;
    }

    public List<CreditScore> findAll() throws SQLException {
        List<CreditScore> creditScores = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit_scores");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            CreditScore creditScore = new CreditScore();
            creditScore.setId(resultSet.getInt("id"));
            creditScore.setScore(resultSet.getInt("score"));
            creditScore.setSalary(resultSet.getDouble("salary"));
            creditScore.setNetWorth(resultSet.getDouble("net_worth"));
            creditScore.setTotalLoans(resultSet.getDouble("total_loans"));
            creditScore.setRepaidLoans(resultSet.getDouble("repaid_loans"));
            creditScore.setOccupation(resultSet.getString("occupation"));
            creditScores.add(creditScore);
        }

        resultSet.close();
        statement.close();
        return creditScores;
    }

    public void save(CreditScore creditScore) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO credit_scores(score, salary, net_worth, total_loans, repaid_loans, occupation) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, creditScore.getScore());
        statement.setDouble(2, creditScore.getSalary());
        statement.setDouble(3, creditScore.getNetWorth());
        statement.setDouble(4, creditScore.getTotalLoans());
        statement.setDouble(5, creditScore.getRepaidLoans());
        statement.setString(6, creditScore.getOccupation());
        statement.executeUpdate();
        statement.close();
    }

    public void update(CreditScore creditScore) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE credit_scores SET score = ?, salary = ?, net_worth = ?, total_loans = ?, repaid_loans = ?, occupation = ? WHERE id = ?");
        statement.setInt(1, creditScore.getScore());
        statement.setDouble(2, creditScore.getSalary());
        statement.setDouble(3, creditScore.getNetWorth());
        statement.setDouble(4, creditScore.getTotalLoans());
        statement.setDouble(5, creditScore.getRepaidLoans());
        statement.setString(6, creditScore.getOccupation());
        statement.setInt(7, creditScore.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM credit_scores WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }
}
