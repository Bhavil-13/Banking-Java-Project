package Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    Connection dbConnection;

    public TransactionDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    public int addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction(date_time, sender_id, receiver_id, amount, trans_context, loan_id) "
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, transaction.getDateTime());
            pstmt.setInt(2, transaction.getSenderId());
            pstmt.setInt(3, transaction.getReceiverId());
            pstmt.setDouble(4, transaction.getAmount());
            pstmt.setString(5, transaction.getTransactionContext());
            pstmt.setInt(6, transaction.getLoanId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating transaction failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating transaction failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return -1;
    }

    public List<Transaction> getTransactionsByPersonId(int person_id) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT id, date_time, sender_id, receiver_id, amount, trans_context, loan_id "
                + "FROM transaction WHERE sender_id = ? OR receiver_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);
            pstmt.setInt(2, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int transaction_id = rs.getInt("id");
                String date_time = rs.getString("date_time");
                int sender_id = rs.getInt("sender_id");
                int receiver_id = rs.getInt("receiver_id");
                double amount = rs.getDouble("amount");
                String trans_context = rs.getString("trans_context");
                int loan_id = rs.getInt("loan_id");

                Transaction transaction = new Transaction(date_time, sender_id, receiver_id, amount, trans_context,
                        loan_id);
                transaction.setId(transaction_id);
                transactions.add(transaction);
            }
            return transactions;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
}
