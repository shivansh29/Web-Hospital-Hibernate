package Servlets;

import DAO.DB;
import databaseClasses.customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class appointCustomer extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
        customer cust=(customer)session.getAttribute("cust");
        try{
            String name=cust.getName();
            int age=cust.getAge();
            String gender=cust.getGender();
            String email=cust.getMobile();
            String problem=request.getParameter("prob");
            String time=request.getParameter("time");
            String date=request.getParameter("dob");
            System.out.println(date);
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date,formatter);
            /*SimpleDateFormat sd=new SimpleDateFormat("dd/mm/yyyy");
            System.out.println(sd);
            java.util.Date sdt=sd.parse(date);
            System.out.println(sdt);*/
            java.sql.Date dt = java.sql.Date.valueOf( localDate );
            System.out.println(dt);
            
            DB db=new DB();
            HashMap map=db.appoint(problem, dt, time, cust.getEmail());
            if(map==null){
                session.setAttribute("msg","exception");
            }
            else if(map.get("docName")==null){
                session.setAttribute("msg","Please select some other Date/Time slot");
            }
            else{
                session.setAttribute("msg","Your appointment is fixed with "+map.get("docName")+" on "+map.get("date")+" at "+map.get("time"));
                
            }
                
                response.sendRedirect("appoint.jsp");
                
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Appointment problem");
        }
    }
}
