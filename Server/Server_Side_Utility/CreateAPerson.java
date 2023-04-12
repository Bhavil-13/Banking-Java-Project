package Server_Side_Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import models.Person;
import models.PersonDAO;

/**
 * Servlet implementation class sample
 */
@WebServlet("/CreateAPerson")
@MultipartConfig
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter write=response.getWriter();

		// Part p1=request.getParameter("name");
		// Part p2=request.getPart("address");

		String name=request.getParameterValues("name")[0];
		String address=request.getParameterValues("address")[0];
		response.getWriter().append("Served at: "+name+address).append(request.getContextPath());
		Person p=new Person();
		p.setAddress(address);
		p.setName(name);
		write.println("Error Writings Starts here");
		w=write;
		tryCreatingAPerson();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");

		// Part p1=request.getParameter("name");
		// Part p2=request.getPart("address");

		String name=req.getParameterValues("name")[0];
		String address=req.getParameterValues("address")[0];
		resp.getWriter().append("Served at: "+name+address).append(req.getContextPath());
		Person p=new Person();
		p.setAddress(address);
		p.setName(name);

		tryCreatingAPerson();
		super.doPost(req, resp);
	}
	public static void main(String[] args) {
		tryCreatingAPerson();
	}

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
	}

}
