import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOObjects.DatabaseConnection;


public class example extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException
    {
    res.setContentType("text/html");//setting the content type
    PrintWriter pw=res.getWriter();//get the stream to write the data

    //writing html in the stream
    pw.println("<html><body>");
    pw.println("Welcome to servlet");
    pw.println("</body></html>");
    pw.close();//closing the stream
    try {

        // Initialize the database
        Connection con = DatabaseConnection.initializeDatabase();

        // Create a SQL query to insert data into demo table
        // demo table consists of two columns, so two '?' is used
        PreparedStatement st = con
               .prepareStatement("insert into person values('bulbul', 'bul')");

        // For the first parameter,
        // get the data using request object
        // sets the data to st pointer
        // st.setInt(1, Integer.valueOf(req.getParameter("name")));

        // // Same for second parameter
        // st.setString(2, req.getParameter("address"));

        // Execute the insert command using executeUpdate()
        // to make changes in database
        st.executeUpdate();

        // Close all the connections
        st.close();
        con.close();

        // Get a writer pointer
        // to display the successful result
        pw.println("<html><body><b>Successfully Inserted"
                    + "</b></body></html>");
    }
    catch (Exception e) {
        pw.print(e.getMessage());
        pw.println(e.getStackTrace());

        e.printStackTrace();
    }
    pw.println(getServletInfo()+"Hello Post");
    }
}
