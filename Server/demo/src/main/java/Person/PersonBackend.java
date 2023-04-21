package Person;


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

public class PersonBackend extends HttpServlet {
    DAO_Factory daoFactory;
    PersonDAO dao;
    PrintWriter out;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out = resp.getWriter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader read = req.getReader();
            Person person = mapper.readValue(read, Person.class);

            System.out.println(req.getRequestURL());
            // /demo/Login
            String url = req.getQueryString();
            daoFactory=new DAO_Factory();
            daoFactory.activateConnection();
            dao=daoFactory.getPersonDAO();
            System.out.println(url);
            if (url.contains("login")){
                out.print(Login(person));
            }
            if (url.contains("register")){
                out.print(Register(person));
                daoFactory.deactivateConnection(TXN_STATUS.COMMIT);
            }
            // Carry out DB operations using DAO
            // PersonDAO sdao = daoFactory.getpersonDAO();
            // sdao.createPerson(person, out);

            // out.println("the response")
        } catch (Exception e) {
            out.print(e.getMessage());
            out.println(e.getStackTrace());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String Register(Person person) {
        try {
            Integer id=dao.addPerson(person.getName(), person.getAddress(), person.getEmail(), person.getPassword());
            // Status zero means success
            System.out.println("register");
            if(id.equals(-1)){
            return "{\"Status\":\"1\",\"personid\":\"null\"}";

            }
            return "{\"Status\":\"0\",\"personid\":\""+id.toString()+"\"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means already exist
            Integer id=dao.getPersonByEmail(person.getEmail()).getId();
            System.out.println("register error");
            e.printStackTrace();
            return "{\"Status\":\"1\",\"personid\":\""+id.toString()+"\"}";
        }
    }

    private String Login(Person person) {
        try {
            Person persontest=dao.getPersonByEmail(person.getEmail());
            if(persontest.getPassword().equals(person.getPassword())){

                // Status zero means success
                System.out.println("register");
                return "{\"Status\":\"0\",\"personid\":\""+persontest.getId().toString()+"\"}";
            }else{
                System.out.println("register");
                return "{\"Status\":\"1\",\"personid\":\"null\"}";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Status one means incorrect password
            Integer id=dao.getPersonByEmail(person.getEmail()).getId();
            e.printStackTrace();
            return "{\"Status\":\"1\",\" personid\":\"-1\"}";
        }
    }
}