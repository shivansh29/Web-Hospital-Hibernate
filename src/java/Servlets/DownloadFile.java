package Servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DownloadFile extends HttpServlet {

protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            HttpSession session=request.getSession();
            try{
                File f= new File("C:\\Users\\Narci\\Desktop\\frontend\\Myqr3.jpg");
                InputStream targetStream = new FileInputStream(f);
                byte[] b = new byte[1024];
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int c;
                while ((c = targetStream.read(b)) != -1) {
                    os.write(b, 0, c);
                }
                b= os.toByteArray();
                if(b!=null){
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition","attachment; filename="+f.getName()); 
                response.getOutputStream().write(b);
            }
            }catch(Exception e){
                System.out.println("Downloading file problem");
                e.printStackTrace();
                
            }
}
}
