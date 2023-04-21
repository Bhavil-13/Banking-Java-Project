package Loans;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO_factory.DAO_Factory;
import DAO_factory.DAO_Factory.TXN_STATUS;

public class LoanBackend extends HttpServlet {
    DAO_Factory daoFactory;
    LoanDAO dao;
    PrintWriter out;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out = resp.getWriter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read = req.getReader();
            Loan Loan = mapper.readValue(read, Loan.class);

            System.out.println(req.getRequestURL());
            // /demo/Login
            String url = req.getQueryString();
            daoFactory=new DAO_Factory();
            daoFactory.activateConnection();
            dao=daoFactory.getLoanDAO();
            System.out.println(url);
            if (url.contains("createLoan")){
                out.print(CreateLoan(Loan));
            }
            if (url.contains("getLoansByID")){
                GetAllLoans();
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

    private String CreateLoan(Loan loan) {
        try {
            Integer id=dao.addLoan(loan);;
            System.out.println("Loan created");
            return "{\"Status\":\"0\",\" Loanid\":\""+id.toString()+" \"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means already exist
            System.out.println("Loan error");
            e.printStackTrace();
            return "{\"Status\":\"1\",\" Loanid\":\"null\"}";
        }
    }
    private String ApproveLoan(Loan loan) {
        try {
            dao.updateLoan(loan);
            System.out.println("Loan created");
            return "{\"Status\":\"0\"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means already exist
            System.out.println("Loan error");
            e.printStackTrace();
            return "{\"Status\":\"1\",\" Loanid\":\"null\"}";
        }
    }
    private String GetAllLoans() {
        try {
            List<Loan> a=dao.getAllLoans();
            out.write("{");
            System.out.println("Loan created");
            for (int i = 0; i < a.size(); i++) {
                System.out.println(a.get(i).getloansjson());
                out.write(a.get(i).getloansjson()+",");
            }
            out.write("}");
            return "";
        } catch (Exception e) {
            System.out.println("Loan error");
            e.printStackTrace();
            return "";
        }
    }

}