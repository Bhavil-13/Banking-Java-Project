package Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersonDAO {
    // Dao for person class here

    Connection dbConnection;

    public PersonDAO(Connection dbconn) {
        dbConnection = dbconn;
    }

    public Person getPersonByKey(int person_id) {
        Person p = new Person(null, null, null, null);
        String sql;
        sql = "SELECT name, address, email, password FROM person WHERE id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, person_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                break;
            }
            return p;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public Person getPersonByEmail(String email) {
        Person p = new Person(null, null, null, null);
        String sql;
        sql = "SELECT person_id, name, address, email, password FROM person WHERE email = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt("person_id"));
                p.setName(rs.getString("name"));
                p.setAddress(rs.getString("address"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                break;
            }
            return p;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    public int addPerson(String name, String address, String email, String password) {
        String sql;
        sql = "INSERT INTO person(name, address, email, password) values(?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next())
                return keys.getInt(1);
            return -1;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return -1;
    }
}
