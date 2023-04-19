package DAOObjects;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Transaction;
public class TransactionDAO {

    private final Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (accountid, senderid, receiverid, amount, context, referenceid, date_time) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, transaction.getAccountid());
        statement.setInt(2, transaction.getSenderid());
        statement.setInt(3, transaction.getReceiverid());
        statement.setDouble(4, transaction.getAmount());
        statement.setString(5, transaction.getContext());
        statement.setString(6, transaction.getReferenceid());
        statement.setTimestamp(7, Timestamp.valueOf(transaction.getDate_time()));
        statement.executeUpdate();
        statement.close();
    }

    public Transaction read(int transactionId) throws SQLException {
        String query = "SELECT * FROM transactions WHERE transactionid = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, transactionId);
        ResultSet resultSet = statement.executeQuery();
        Transaction transaction = null;
        if (resultSet.next()) {
            transaction = new Transaction();
            transaction.setAccountid(resultSet.getInt("accountid"));
            transaction.setSenderid(resultSet.getInt("senderid"));
            transaction.setReceiverid(resultSet.getInt("receiverid"));
            transaction.setAmount(resultSet.getDouble("amount"));
            transaction.setContext(resultSet.getString("context"));
            transaction.setReferenceid(resultSet.getString("referenceid"));
            transaction.setDate_time(resultSet.getTimestamp("date_time").toLocalDateTime());
        }
        resultSet.close();
        statement.close();
        return transaction;
    }

    public void update(Transaction transaction) throws SQLException {
        String query = "UPDATE transactions SET accountid = ?, senderid = ?, receiverid = ?, amount = ?, " +
                "context = ?, referenceid = ?, date_time = ? WHERE transactionid = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, transaction.getAccountid());
        statement.setInt(2, transaction.getSenderid());
        statement.setInt(3, transaction.getReceiverid());
        statement.setDouble(4, transaction.getAmount());
        statement.setString(5, transaction.getContext());
        statement.setString(6, transaction.getReferenceid());
        statement.setTimestamp(7, Timestamp.valueOf(transaction.getDate_time()));
        statement.setInt(8, transaction.getTransactionId());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(int transactionId) throws SQLException {
        String query = "DELETE FROM transactions WHERE transactionid = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, transactionId);
        statement.executeUpdate();
        statement.close();
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        String query = "SELECT * FROM transactions";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Transaction> transactions = new ArrayList<>();
        while (resultSet.next()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionid(resultSet.getInt("transactionid"));
            transaction.setAccountid(resultSet.getInt("accountid"));
            transaction.setSenderid(resultSet.getInt("senderid"));
            transaction.setReceiverid(resultSet.getInt("receiverid"));
            transaction.setAmount(resultSet.getDouble("amount"));
            transaction.setContext(resultSet.getString("context"));
            transaction.setReferenceid(resultSet.getString("referenceid"));
            transaction.setDate_time(resultSet.getTimestamp("date_time").toLocalDateTime());
            transactions.add(transaction);
        }
        resultSet.close();
        statement.close();
        return transactions;
    }
}


