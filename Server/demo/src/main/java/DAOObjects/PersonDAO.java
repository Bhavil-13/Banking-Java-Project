package DAOObjects;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Person;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.*;
public class PersonDAO {
	Connection dbConnection;
	public PersonDAO(Connection dbconn){
		dbConnection = dbconn;
	}
	public void createPerson(Person person,PrintWriter out){
		Integer id;
	try {
	Connection con = DatabaseConnection.initializeDatabase();
	PreparedStatement st = con
		   .prepareStatement("insert into person(name,address,email,password) values(?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
	st.setString(1, person.getName());
	st.setString(2, person.getAddress());
	st.setString(3, person.getEmail());
	st.setString(4, person.getPassword());
	try {
		id=st.executeUpdate();

	} catch (SQLIntegrityConstraintViolationException e) {
	st=con.prepareStatement("select personid from person where email=?");
	 st.setString(1, person.getEmail());
	ResultSet ids=st.executeQuery();
	ids.next();
	id = ids.getInt(1);
	st.close();
	con.close();
	}
	out.print("{\"personid\"="+id.toString(0)+"}");
	// return id;
}
catch (Exception e) {
	e.printStackTrace();
}
	// return id;

	}
	public Person getPersonByKey(int personid) {
		Person s=null;
		String sql;
		PreparedStatement stmt = null;
		try{
			sql = "select * from Person where personid=?";
			stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, personid);
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			s=new Person(rs.getString("name"),
			rs.getString("name"),
			rs.getString("name"),
			rs.getString("name"));
			return s;
		} catch (SQLException ex) {
		    // handle any errors
			ex.printStackTrace();
		}
		// Add exception handling when there is no matching record
		return s;
	}
}
