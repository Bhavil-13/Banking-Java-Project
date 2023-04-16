
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

import DAOObjects.DAO_Factory;
import DAOObjects.DatabaseConnection;
import DAOObjects.PersonDAO;
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
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read=req.getReader();
            Person person = mapper.readValue(read,Person.class);
            daoFactory.activateConnection();

			// Carry out DB operations using DAO
			PersonDAO sdao = daoFactory.getpersonDAO();
			sdao.createPerson(person,out);
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
