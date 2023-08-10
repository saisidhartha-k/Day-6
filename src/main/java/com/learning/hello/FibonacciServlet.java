package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class FibonacciServlet
 */
@WebServlet("/FibonacciServlet")
public class FibonacciServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>Fibonacci Calculator</h2>");
        out.println("<form method=\"post\">");
        out.println("Number of Fibonacci numbers: <input type=\"text\" name=\"count\"><br>");
        out.println("<input type=\"submit\" value=\"Generate\">");
        out.println("</form>");
        out.println("</body></html>");
        
    }
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String numberString = request.getParameter("count");
        int number = 10;
        
        if(!numberString.isEmpty())
        number = Integer.parseInt(numberString);
        
        out.println("<html> <body>");
        out.println("<h2>Fibonacci Sequence</h2>");
        out.println("<p>Generating the first " + number + " Fibonacci numbers:</p>");
        
        long[] fib = new long[number];
        fib[0] = 0;
        fib[1] = 1;
        
        for (int i = 2; i < number; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        
        for (int i = 0; i < number; i++) {
            out.print(fib[i] + " , ");
        }
        
        out.println("</body></html>");
    }
}
