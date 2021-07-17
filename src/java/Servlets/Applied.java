package Servlets;

import DAO.DB;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class Applied extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session=request.getSession();
        try{
            String fname=request.getParameter("fname");
            String sub=request.getParameter("id");
            int id=Integer.parseInt(sub.substring(0,sub.length()-1));
            String lname=request.getParameter("lname");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String email=request.getParameter("email");
            String mobile=request.getParameter("mobile");
            String address=request.getParameter("address");
            int exp=Integer.parseInt(request.getParameter("exp"));
            String gender=request.getParameter("inlineRadioOptions");
            Part p=request.getPart("resume");
            InputStream in=p.getInputStream();
            
            //Input Stream to byte array
            ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		// read bytes from the input stream and store them in buffer
		while ((len = in.read(buffer)) != -1) {
			// write bytes from the buffer into output stream
			os.write(buffer, 0, len);
		}

		 buffer=os.toByteArray();

                 DB db=new DB();
                 String s1=db.saveCanidate(id, fname, lname, city, state, gender, address, exp, buffer, mobile, email);
                 if(s1.equalsIgnoreCase("done"))
                     session.setAttribute("msg","Applied successfully! Do Not Apply multiple times");
                 else
                     session.setAttribute("msg","Aplication Failed!");
                     
                     response.sendRedirect("TotalJobs.jsp");
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("applied problemmmmmmmmmmmmmmmmmmm");
        }
    }
}
