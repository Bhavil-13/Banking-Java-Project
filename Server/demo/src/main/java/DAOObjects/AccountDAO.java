package DAOObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Account;

public class AccountDAO{
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection=connection;
    }

    public void create(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts (account_id, person_id, balance) VALUES (?, ?, ?)");
            statement.setInt(1, account.getAccount());
            statement.setInt(2, account.getPerson_id());
            statement.setDouble(3, account.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account read(int account_id) {
        Account account = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE account_id = ?");
            statement.setInt(1, account_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setAccount(resultSet.getInt("account_id"));
                account.setPerson_id(resultSet.getInt("person_id"));
                account.setBalance(resultSet.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void update(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET person_id = ?, balance = ? WHERE account_id = ?");
            statement.setInt(1, account.getPerson_id());
            statement.setDouble(2, account.getBalance());
            statement.setInt(3, account.getAccount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Account account) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM accounts WHERE account_id = ?");
            statement.setInt(1, account.getAccount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
