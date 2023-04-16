
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Person;
// import models.Person;
// import models.PersonDAO;

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
            // req.get
            // String jsonString = IOUtils.toString(req.getInputStream() null);
            // req.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read=req.getReader();
            // System.out.println(read.readLine());
            // read.
            // read.readLine();
            Person person = mapper.readValue(read,Person.class);

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into person(name,address,email,password) values(?,?,?,?);");

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
            try {

                st.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                // TODO: handle exception
                st=con.prepareStatement("select personid from person where email=?");
             // sets the data to st pointer
             st.setString(1, person.getEmail());

            ResultSet ids=st.executeQuery();
            ids.next();
            Integer id = ids.getInt(1);


            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer
            // to display the successful result
            out.println("<html><body><b>Failed to create Account:Already Present with given email:"+id.toString()
                        + "</b></body></html>");

                return;

            }
        st=con.prepareStatement("select personid from person where email=?");
             // sets the data to st pointer
             st.setString(1, person.getEmail());

            ResultSet ids=st.executeQuery();
            ids.next();
            Integer id = ids.getInt(1);


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
        doGet(req, resp);
    }

}
