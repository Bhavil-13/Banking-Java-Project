package Transaction;


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

public class TransactionBackend extends HttpServlet {
    DAO_Factory daoFactory;
    TransactionDAO dao;
    PrintWriter out;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out = resp.getWriter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read = req.getReader();
            Transaction transaction = mapper.readValue(read, Transaction.class);

            System.out.println(req.getRequestURL());
            // /demo/Login
            String url = req.getQueryString();
            daoFactory=new DAO_Factory();
            daoFactory.activateConnection();
            dao=daoFactory.getTransactionDAO();
            System.out.println(url);
            if (url.contains("transaction")){
                out.print(DoTransaction(transaction));
            }
            daoFactory.deactivateConnection(TXN_STATUS.COMMIT);
            // Carry out DB operations using DAO
            // TransactionDAO sdao = daoFactory.gettransactionDAO();
            // sdao.createTransaction(transaction, out);

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
    private String DoTransaction(Transaction transaction) {
        try {
            Integer id=dao.addTransaction(transaction);
            // Status zero means success
            System.out.println("applyforloan");
            if(id.equals(-1)){
            return "{\"Status\":\"1\",\" transactionid\":\"null\"}";

            }
            return "{\"Status\":\"0\",\" transactionid\":\""+id.toString()+" \"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means already exist
            System.out.println("register error");
            e.printStackTrace();
            return "{\"Status\":\"1\",\" transactionid\":\"null\"}";
        }
    }
}

