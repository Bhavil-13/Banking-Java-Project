package Account;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_factory.DAO_Factory;
import DAO_factory.DAO_Factory.TXN_STATUS;

public class AccountBackend extends HttpServlet {
    DAO_Factory daoFactory;
    AccountDAO dao;
    PrintWriter out;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out = resp.getWriter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read = req.getReader();
            Account account = mapper.readValue(read, Account.class);

            System.out.println(req.getRequestURL());
            // /demo/Login
            String url = req.getQueryString();
            daoFactory=new DAO_Factory();
            daoFactory.activateConnection();
            dao=daoFactory.getAccountDAO();
            System.out.println(url);
            if (url.contains("createAccount")){
                out.print(CreateAccount(account));
            }
            daoFactory.deactivateConnection(TXN_STATUS.COMMIT);

            // Carry out DB operations using DAO
            // PersonDAO sdao = daoFactory.getpersonDAO();
            // sdao.createPerson(person, out);

            // out.println("the response")
        } catch (Exception e) {
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

    private String CreateAccount(Account account) {
        try {
            Integer id=dao.addAccount(account.getPersonId(), account.getBalance());
            // Status zero means success
            System.out.println("account created");
            if(id.equals(-1)){
            return "{\"Status\":\"1\",\" accountid\":\"null\"}";
            }
            return "{\"Status\":\"0\",\" accountid\":\""+id.toString()+" \"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means already exist
            System.out.println("account error");
            e.printStackTrace();
            return "{\"Status\":\"1\",\" accountid\":\"null\"}";
        }
    }

}