package DAOObjects;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Account;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.*;
public class AccountDAO {
	Connection dbConnection;
	public AccountDAO(Connection dbconn){
		dbConnection = dbconn;
	}
	public void createAccount(Account account,PrintWriter out){
		Integer id;
	try {
	Connection con = DatabaseConnection.initializeDatabase();
	PreparedStatement st = con
		   .prepareStatement("insert into account(name,address,email,password) values(?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
	st.setString(1, account.getName());
	st.setString(2, account.getAddress());
	st.setString(3, account.getEmail());
	st.setString(4, account.getPassword());
	try {
		id=st.executeUpdate();

	} catch (SQLIntegrityConstraintViolationException e) {
	st=con.prepareStatement("select accountid from account where email=?");
	 st.setString(1, account.getEmail());
	ResultSet ids=st.executeQuery();
	ids.next();
	id = ids.getInt(1);
	st.close();
	con.close();
	}
	out.print("{\"accountid\"="+id.toString(0)+"}");
	// return id;
}
catch (Exception e) {
	e.printStackTrace();
}
	// return id;

	}
	public Account getAccountByKey(int accountid) {
		Account s=null;
		String sql;
		PreparedStatement stmt = null;
		try{
			sql = "select * from Account where accountid=?";
			stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, accountid);
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			s=new Account(rs.getString("name"),
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


	public void addAccount(Account Account) {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into Account(accountid, name) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);

			// preparedStatement.setInt(1, Account.getaccountid());
			preparedStatement.setString(2, Account.getName());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Account: Roll No " + Account.getaccountid()
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
}
