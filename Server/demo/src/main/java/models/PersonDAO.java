package models;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class PersonDAO {
	Connection dbConnection;
	public PersonDAO(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	public Boolean createPerson(Person person){
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into Person(name, address) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			preparedStatement.setString(1, person.getName());
			preparedStatement.setString(2, person.getAddress());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Person: Roll No " + Person.getRollno()
				// + ", added to the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
		 return false;

	}
	public Person getPersonByKey(int rollNo) {
		Person s = new Person();
		String sql;
		Statement stmt = null;

		try{
			stmt = dbConnection.createStatement();
			sql = "select rollno, name from Person where rollno = " + rollNo;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int rollno  = rs.getInt("rollno");
				String name = rs.getString("name");
				// s.setRollno(rollno);
				s.setName(name);
				break;
				// Add exception handling here if more than one row is returned
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		return s;
	}


	public void addPerson(Person Person) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into Person(rollno, name) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			// preparedStatement.setInt(1, Person.getRollno());
			preparedStatement.setString(2, Person.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Person: Roll No " + Person.getRollno()
				// + ", added to the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

	public void updatePerson(Person Person) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "update Person set rollno=? , name=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			// preparedStatement.setInt(1, Person.getRollno());
			// preparedStatement.setString(2, Person.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Person: Roll No " + Person.getRollno()
				// + ", updated to the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

	public void deletePerson(Person Person) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "delete from Person(rollno, name) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			// preparedStatement.setInt(1, Person.getRollno());
			preparedStatement.setString(2, Person.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Person: Roll No " + Person.getRollno()
				// + ", deleted from the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}
}
