package Servlets;

import DAO.DB;
import databaseClasses.customer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
@MultipartConfig
public class SendMail extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession session=request.getSession();
            String e=request.getParameter("email");
            DB db=new DB();
            List<customer> p=db.forMail(e);
            Part prescription=request.getPart("prescription");
            System.out.println(prescription);
            java.io.InputStream in=prescription.getInputStream();
            String filename=prescription.getName();
            System.out.println("                          "+filename);
            System.out.println("                          "+prescription.getSubmittedFileName());
            if(p!=null){
                final String SEmail="ashivanshbajpai@gmail.com";
                final String SPass="Shiv@nsh29#";
                final String REmail=e;
                final String Sub="Your prescription is here!";
                final String Body="Your Email Id: "+e+" and Password: "+p;
                //mail send Code
            Properties props=new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","465");
            Session ses=Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SEmail,SPass);
                }
            }
            );
            Message message=new MimeMessage(ses);
            message.setFrom(new InternetAddress(SEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(REmail));
            message.setSubject(Sub);
            
            BodyPart messageBodyPart = new MimeBodyPart();
             messageBodyPart.setText("This is your prescription here");
             Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart(); 
            
           // File filep=new File(prescription);
            DataSource source = new FileDataSource("C:\\Users\\Narci\\Desktop\\frontend\\Myqr3.jpg");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
             message.setContent(multipart);
            
            Transport.send(message);
            session.setAttribute("msg","Mail Sent successfully.");
            response.sendRedirect("Doctor.jsp");
            }else{
                session.setAttribute("msg", "Wrong Emial ID");
                response.sendRedirect("Doctor.jsp");
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("sending mail problem");
        }
        
    }

    
}
