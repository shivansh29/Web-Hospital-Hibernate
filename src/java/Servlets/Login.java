package Servlets;

import DAO.DB;
import databaseClasses.customer;
import databaseClasses.doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
        try{
            String user=request.getParameter("user");
            String pass=request.getParameter("pass");
            String kind=request.getParameter("kind");
            DB db=new DB();
            if(kind.equalsIgnoreCase("customer")){
                customer map=db.customerLogin(user, pass);
                if(map==null){
                    session.setAttribute("msg","Invalid Email or Password");
                    response.sendRedirect("home.jsp");
                }
                else{
                    session.setAttribute("cust",map);
                    session.setAttribute("db",db);
                    response.sendRedirect("customer.html");
                }
            }
            else if(kind.equalsIgnoreCase("admin")){
                if(user.equals("Shivansh")&&pass.equals("shivansh29")){
                    response.sendRedirect("admin.jsp");
                }
                else{
                    session.setAttribute("msg","Invalid Email or Password");
                    response.sendRedirect("home.jsp");
                }
            }
            else{
                doctor check=db.EmployeeLogin(user, pass);
                if(check==null){
                    session.setAttribute("msg","Invalid Email or Password");
                    response.sendRedirect("home.jsp");
                }
                else if(check.getField().equalsIgnoreCase("doctor")){
                    session.setAttribute("emp", check);
                    response.sendRedirect("Doctor.jsp");
                }
                else
                    response.sendRedirect("QA.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Login Problem");
        }
    }
}
