package Servlets;

import DAO.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostJob extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
        
        try{
            String pos=request.getParameter("pos");
            int experience=Integer.parseInt(request.getParameter("experience"));
            int sal=Integer.parseInt(request.getParameter("sal"));
            String descrip=request.getParameter("descrip");
            
            DB db=new DB();
            String s1=db.saveJob(pos, experience, sal, descrip);
            if(s1.equalsIgnoreCase("done")){
                session.setAttribute("msg1", "Added Succesfully");
            }
            else
                session.setAttribute("msg1", "Adding job not successfull");
                
            response.sendRedirect("admin.jsp");
            
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("PostJob problemmmmmmmmmmmmmmmmmmmmmm");
        }
        
    }
}
