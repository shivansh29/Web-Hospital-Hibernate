package Servlets;

import DAO.DB;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class Pay extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    HttpSession session=request.getSession();
    try{
        Part p=request.getPart("image");
        java.io.InputStream in=p.getInputStream();
            Result re=new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(in)))));
            String text=re.getText();
             StringTokenizer st = new StringTokenizer(text,"-");  
             DB db=new DB();
             HashMap<String ,Double> map=db.getPrice();
             double price=0;
             while (st.hasMoreTokens()){
                 String name=st.nextToken();
                 int qty=Integer.parseInt(st.nextToken());
                 price+=(qty*map.get(name));
                 System.out.println(name+"   "+qty);
             }
             session.setAttribute("price",price);
             response.sendRedirect("Payment.jsp");
            System.out.println(price);
    }catch(Exception e){
        System.out.println("pay problem");
        e.printStackTrace();
    }
}
}
