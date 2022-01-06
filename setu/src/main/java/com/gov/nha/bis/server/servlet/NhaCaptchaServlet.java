package com.gov.nha.bis.server.servlet;
/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gov.nha.bis.server.controller.CscLoginController;

@WebServlet("/captach")
public class NhaCaptchaServlet extends HttpServlet {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2520844911845749663L;
	public static final String FILE_TYPE = "jpeg";
	
	private static final Logger logger = LoggerFactory.getLogger(NhaCaptchaServlet.class);
	

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {

       response.setHeader("Cache-Control", "no-cache");
       response.setDateHeader("Expires", 0);
       response.setHeader("Pragma", "no-cache");
       response.setDateHeader("Max-Age", 0);

       String captchaStr=generateCaptcha(6);
       try {
           int width=160;      
           int height=35;

           Color bg = new Color(0,255,255);
           Color fg = new Color(0,100,0);

           Font font = new Font("Arial", Font.BOLD, 20);
           BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
           Graphics g = cpimg.createGraphics();

           g.setFont(font);
           g.setColor(bg);
           g.fillRect(0, 0, width, height);
           g.setColor(fg);
           g.drawString(captchaStr,10,25);   

           HttpSession session = request.getSession(true);
           session.setAttribute("CAPTCHA", captchaStr);

           logger.info("captchaStr==="+captchaStr);
          OutputStream outputStream = response.getOutputStream();

          ImageIO.write(cpimg, FILE_TYPE, outputStream);
        
          outputStream.close();

          
          
          
       } catch (Exception e) {
               e.printStackTrace();
       }
   }

   private String generateCaptcha(int captchaLength) {

	   String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	   StringBuffer captchaStrBuffer = new StringBuffer();
	   Random rnd = new Random();

	   while (captchaStrBuffer.length() < captchaLength)
	   {
		   int index = (int) (rnd.nextFloat() * saltChars.length());
		   captchaStrBuffer.append(saltChars.substring(index, index+1));
	   }

	   return captchaStrBuffer.toString();

}

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
       doPost(request, response);
   }


}
