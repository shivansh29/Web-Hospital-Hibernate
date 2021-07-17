package Servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.glxn.qrgen.image.ImageType;
public class QRCode extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            HttpSession session=request.getSession();
        String names[]=request.getParameterValues("medi");
        String doses[]=request.getParameterValues("dose");
        String details="";
        for(int i=0;i<names.length;i++){
            details+=names[i]+"-"+doses[i];
            details+="---";
        }
        ByteArrayOutputStream out= net.glxn.qrgen.QRCode.from(details).to(ImageType.JPG).stream();
        File f= new File("C:\\Users\\Narci\\Desktop\\frontend\\Myqr3.jpg");
        System.out.println(f.getAbsolutePath());
        FileOutputStream fs= new FileOutputStream(f);
        fs.write(out.toByteArray());
        fs.flush();
        session.setAttribute("fileReady","file ready");
        response.sendRedirect("Doctor.jsp");
    }
}
