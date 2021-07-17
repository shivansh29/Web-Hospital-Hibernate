package Servlets;

import DAO.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Complain extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            HttpSession session=request.getSession();
            try{
                String name=request.getParameter("name");
                int age=Integer.parseInt(request.getParameter("age"));
                String email=request.getParameter("email");
                String comp=request.getParameter("comp");
                String phone=request.getParameter("phone");
                String gender=request.getParameter("inlineRadioOptions");
                DB db=new DB();
                String s1=db.saveComplain(name, age, comp, email,phone,gender);
                if(s1.equalsIgnoreCase("done")){
                    session.setAttribute("msg","We got you");
                    response.sendRedirect("complain.jsp");
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Complain problem");
            }
}
}
