package Servlets;

import DAO.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Hire extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session=request.getSession();
    try{
            String name=request.getParameter("name");
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            int age=Integer.parseInt(request.getParameter("age"));
            String address=request.getParameter("address");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String field=request.getParameter("field");
            String d=request.getParameter("dob");
            SimpleDateFormat sd=new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date sdt=sd.parse(d);
            java.sql.Date dt=new java.sql.Date(sdt.getTime());
            String aadhar=request.getParameter("aadhar");
            String pan=request.getParameter("pan");
            String college=request.getParameter("college");
            String gender=request.getParameter("inlineRadioOptions");
            String mobile=request.getParameter("mobile");
            
            DB db=new DB();
            String s1=db.EmployeeRegister(name, age, email, pass, mobile, address, city, state, dt, gender, aadhar, pan, field,college);
            if(s1.equalsIgnoreCase("exception")){
                session.setAttribute("msg","Email already exists");
                response.sendRedirect("Hire.jsp");}
            else{
                session.setAttribute("msg1", "Employee Added Successfully");
                response.sendRedirect("admin.jsp");
            }
    }catch(Exception e){
        System.out.println("Hire Problem");
        e.printStackTrace();
    }
}

}
