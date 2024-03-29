import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class StudentDAO_JDBC implements StudentDAO {
	Connection dbConnection;
	public StudentDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public Student getStudentByKey(int rollNo) {
		Student s = new Student();
		String sql;
		Statement stmt = null;

		try{
			stmt = dbConnection.createStatement();
			sql = "select rollno, name from student where rollno = " + rollNo;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int rollno  = rs.getInt("rollno");
				String name = rs.getString("name");
				s.setRollno(rollno);
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

	@Override
	public void addStudent(Student student) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into student(rollno, name) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			preparedStatement.setInt(1, student.getRollno());
			preparedStatement.setString(2, student.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Student: Roll No " + student.getRollno()
				+ ", added to the database");
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

	@Override
	public void updateStudent(Student student) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "update student set rollno=? , name=?";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			preparedStatement.setInt(1, student.getRollno());
			preparedStatement.setString(2, student.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Student: Roll No " + student.getRollno()
				+ ", updated to the database");
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

	@Override
	public void deleteStudent(Student student) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "delete from student(rollno, name) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			preparedStatement.setInt(1, student.getRollno());
			preparedStatement.setString(2, student.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Student: Roll No " + student.getRollno()
				+ ", deleted from the database");
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
