package DAOObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Person;

public class PersonDAO {
    private Connection conn;

    private final String INSERT_QUERY = "INSERT INTO person(name, address, email, password) VALUES(?, ?, ?, ?)";
    private final String SELECT_QUERY = "SELECT * FROM person WHERE person_id_number = ?";
    private final String UPDATE_QUERY = "UPDATE person SET name = ?, address = ?, email = ?, password = ? WHERE person_id_number = ?";
    private final String DELETE_QUERY = "DELETE FROM person WHERE person_id_number = ?";

    public PersonDAO(Connection dbconnection) {
        this.conn=dbconnection;
    }

    // Create operation
    public Integer addPerson(Person person) throws SQLException {
        try (
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getAddress());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getPassword());
            return pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // e.printStackTrace();
			return -1;
        }
    }

    // Read operation
    public Person getPersonById(int id) {
        Person person = null;
        try (
             PreparedStatement pstmt = conn.prepareStatement(SELECT_QUERY)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String password = rs.getString("password");
                person = new Person(name, address, email, password, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    // Update operation
    public void updatePerson(Person person) {
        try (
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getAddress());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getPassword());
            pstmt.setInt(5, person.getPersonID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation
    public void deletePerson(int id) {
        try (
             PreparedStatement pstmt = conn.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all persons
    public List<Person> getAllPersons() throws SQLException {
        List<Person> people = new ArrayList<>();
        try (
             Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM person";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int id = rs.getInt("person_id_number");
                Person person = new Person(name,address,email,password,id);
				people.add(person);
			}
            rs.close();
            stmt.close();
			return people;
		}
	}
}
