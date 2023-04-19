package LoanApplication;


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

public class LoanApplicationBackend extends HttpServlet {
    DAO_Factory daoFactory;
    LoanApplicationDAO dao;
    PrintWriter out;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out = resp.getWriter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read = req.getReader();
            LoanApplication loanApplication = mapper.readValue(read, LoanApplication.class);

            System.out.println(req.getRequestURL());
            // /demo/Login
            String url = req.getQueryString();
            daoFactory=new DAO_Factory();
            daoFactory.activateConnection();
            dao=daoFactory.getLoanApplicationDAO();
            System.out.println(url);
            if (url.contains("applyForLoan")){
                out.print(ApplyForLOAN(loanApplication));
            }
            daoFactory.deactivateConnection(TXN_STATUS.COMMIT);
            // Carry out DB operations using DAO
            // LoanApplicationDAO sdao = daoFactory.getloanApplicationDAO();
            // sdao.createLoanApplication(loanApplication, out);

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

    private String ApplyForLOAN(LoanApplication loanApplication) {
        try {
            Integer id=dao.createLoanApplication(loanApplication.getAccountId(),loanApplication.getApplicantId(), loanApplication.getAmount(), loanApplication.getReason()).getId();
            // Status zero means success
            System.out.println("applyforloan");
            if(id.equals(-1)){
            return "{\"Status\":\"1\",\" loanApplicationid\":\"null\"}";

            }
            return "{\"Status\":\"0\",\" loanApplicationid\":\""+id.toString()+" \"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means already exist
            System.out.println("register error");
            e.printStackTrace();
            return "{\"Status\":\"1\",\" loanApplicationid\":\"null\"}";
        }
    }
}

