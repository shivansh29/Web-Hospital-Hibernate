/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DAO.DB;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Narci
 */
public class Register extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            HttpSession session=request.getSession();
            try{
            String name=request.getParameter("name");
            int age=Integer.parseInt(request.getParameter("age"));
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            String ph=request.getParameter("phone");
            String address=request.getParameter("address");
            String city=request.getParameter("city");
            String st=request.getParameter("state");
            String d=request.getParameter("dob");
            
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate localDate = LocalDate.parse(d,formatter);
            /*SimpleDateFormat sd=new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date sdt=sd.parse(d);*/
             java.sql.Date dt = java.sql.Date.valueOf( localDate );
            String gender=request.getParameter("inlineRadioOptions");
            
            DB db=new DB();
            System.out.println("date");
            String s1=db.saveCustomer(name, age, email, pass, ph, address, city, st, dt, gender);
            if(s1.equalsIgnoreCase("exception")){
                session.setAttribute("msg","Email already exists");
                response.sendRedirect("Register.jsp");
            }else{
            session.invalidate();
            response.sendRedirect("home.jsp");}
            }
            catch(Exception e){
                e.printStackTrace();
            response.sendRedirect("error.jsp");    
            System.out.println("Register problem");
                
            }
        }
}
