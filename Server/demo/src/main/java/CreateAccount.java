
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Result;

import models.Person;
// import models.Person;
// import models.PersonDAO;

/**
 * Servlet implementation class sample
 */
public class CreateAccount extends HttpServlet {
	public static DAO_Factory daoFactory;

	private static final long serialVersionUID = 1L;
	static PrintWriter w;
	// private static PrintWriter;

    /**
     * Default constructor.
     */
    public CreateAccount() {
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
            // req.get
            // String jsonString = IOUtils.toString(req.getInputStream() null);
            // req.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Person person = mapper.readValue(req.getInputStream(),Person.class);

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into person values(?,?,?,?);");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            st.setString(1, person.getName());

            // Same for second parameter
            st.setString(2, person.getAddress());
            // Same as third parameter
            st.setString(3, person.getEmail());

            // Same for second parameter
            st.setString(4, person.getPassword());

            // Execute the insert command using executeUpdate()
            // to make changes in database
            Integer i= st.executeUpdate();
            st=con.prepareStatement("select personid from person where name=? and address=? and email=? and password=? ");
             // sets the data to st pointer
             st.setString(1, person.getName());

             // Same for second parameter
             st.setString(2, person.getAddress());
             // Same as third parameter
             st.setString(3, person.getEmail());

             // Same for second parameter
             st.setString(4, person.getPassword());
            ResultSet ids=st.executeQuery();
            Integer id=ids.getInt(1);

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer
            // to display the successful result
            out.println("<html><body><b>Successfully Created Account"+id.toString()
                        + "</b></body></html>");
        }
        catch (Exception e) {
			out.print(e.getMessage());
			out.println(e.getStackTrace());

            e.printStackTrace();
        }
		// out.println(getServletInfo()+"Hello Post");

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		try {

             // Initialize the database
             Connection con = DatabaseConnection.initializeDatabase();
             // req.get
             // String jsonString = IOUtils.toString(req.getInputStream() null);
             // req.getInputStream();
             ObjectMapper mapper = new ObjectMapper();
             Person person = mapper.readValue(req.getInputStream(),Person.class);

             // Create a SQL query to insert data into demo table
             // demo table consists of two columns, so two '?' is used
             PreparedStatement st = con
                    .prepareStatement("insert into person values(?, ?)");

             // For the first parameter,
             // get the data using request object
             // sets the data to st pointer
             st.setString(1, person.getName());

             // Same for second parameter
             st.setString(2, person.getAddress());

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

}
