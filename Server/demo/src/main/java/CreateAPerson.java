
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import

import models.Person;
import models.PersonDAO;

/**
 * Servlet implementation class sample
 */
public class CreateAPerson extends HttpServlet {
	public static DAO_Factory daoFactory;

	private static final long serialVersionUID = 1L;
	static PrintWriter w;
	// private static PrintWriter;

    /**
     * Default constructor.
     */
    public CreateAPerson() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		// TODO Auto-generated method stub
		try {

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into person values(?, ?)");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            st.setInt(1, Integer.valueOf(req.getParameter("name")));

            // Same for second parameter
            st.setString(2, req.getParameter("address"));

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer
            // to display the successful result
            out.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>");
        }
        catch (Exception e) {
			out.print(e.getMessage());
			out.println(e.getStackTrace());

            e.printStackTrace();
        }
		out.println(getServletInfo()+"Hello Post");

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		try {

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into person values('?', '?')");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            st.setInt(1, Integer.valueOf(req.getParameter("name")));

            // Same for second parameter
            st.setString(2, req.getParameter("address"));

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer
            // to display the successful result
            out.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>");
        }
        catch (Exception e) {
			out.print(e.getMessage());
			out.println(e.getStackTrace());
            e.printStackTrace();
        }
		out.println(getServletInfo()+"Hello Post");
		super.doPost(req, resp);
	}
	// public static void main(String[] args) {
	// 	// tryCreatingAPerson();
	// }

	private static void tryCreatingAPerson() {
		// w.println("Start test");
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?characterEncoding=latin1&useConfigs=maxPerformance", "root",
                    "");
            Statement stmt = conn.createStatement();
            stmt.execute("insert into Person(name, address) values ('temp','temp');");

//            stmt.execute("delete from employee where ssn='MT2021013';");
            // stmt.execute("update employee set salary=salary*1.1;");
            // stmt.execute("update employee set salary=salary*0.9;");
            // stmt.execute("delete from employee where ssn='MT2021013';");
            // ResultSet rs = stmt.executeQuery("select * from employee");
            // while (rs.next()) {
            //     for(Integer i=1;i<11;i++) {
            //         System.out.print(rs.getString(i)+", ");
            //     }
            //         System.out.println();
            // }
		// w.println("Start test");

        } catch (ClassNotFoundException e) {
			// w.println("Start test");
			// w.println(e.getMessage());
            throw new RuntimeException(e);

        } catch (SQLException e) {
			// w.println("Start test");


            throw new RuntimeException(e);
        }
		// out.println(getServletInfo()+"Hello Post");

	}

}
