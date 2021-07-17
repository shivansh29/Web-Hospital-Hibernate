package Servlets;

import DAO.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteObject extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session=request.getSession();
    try{
        String id=request.getParameter("id");
        String serv=request.getParameter("serv");
        //System.out.println(serv+"   "+id);
        DB db=new DB();
        String s1=db.deleteObject(id, serv);
        
        
        if(s1!=null){
            session.setAttribute("msg1","Doesn't exists");
        }
        else
            session.setAttribute("msg1","Successfull");
        
        
        if(serv.equalsIgnoreCase("comp")){
            response.sendRedirect("QA.jsp");
        }
        else if(serv.equalsIgnoreCase("med")||serv.equalsIgnoreCase("emp")||serv.equalsIgnoreCase("job")){
            response.sendRedirect("admin.jsp");
        }
        
        
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("deleting problem");
    }
}
}
