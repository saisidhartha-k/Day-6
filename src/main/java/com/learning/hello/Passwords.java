package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Passwords extends HttpServlet {
	private static final long serialVersionUID = 1L;
 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_password = request.getParameter("password");	
		PrintWriter out = response.getWriter();
		
		 String filePath = "/home/saisidharthak/eclipse-workspace/dynamic_web/src/main/java/com/learning/hello/BannedPasswords.txt";
		 BufferedReader reader = new BufferedReader(new FileReader(filePath));
		 
		 String line;
         int flag = 0;
		
		out.println("<html> <body>");
		while ((line = reader.readLine()) != null) {
            if(line.equals(user_password))
            {
        
        		flag = 1;

            }
        }
		
		if(flag == 0)
    	out.println(" your password ok");
		
		else
	    	out.println(" your password is blocked");

		out.println("</body></html>");
		
        

	}

}
