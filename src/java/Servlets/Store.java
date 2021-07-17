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


public class Store extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
        try{
            String url=request.getParameter("url");
            String name=request.getParameter("name");
            String price1="Rs"+request.getParameter("price");
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            
            String mfg=request.getParameter("mfg");
            SimpleDateFormat sd=new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date sdt=sd.parse(mfg);
            java.sql.Date dt=new java.sql.Date(sdt.getTime());
            
            String exp=request.getParameter("exp");
            SimpleDateFormat sd1=new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date sdt1=sd.parse(exp);
            java.sql.Date dt1=new java.sql.Date(sdt1.getTime());
            
            String pres=request.getParameter("pres");
            
            DB db=new DB();
            String s1=db.enterMedicine(name, quantity, price1, dt, dt1, pres, url);
            if(s1.equals("done"))
            session.setAttribute("msg","Added Successfully");
            response.sendRedirect("admin.jsp");
        }catch(Exception e){
            System.out.println("storing problem");
            e.printStackTrace();
        }
    }

}
