package com.learning.hello;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;



@WebServlet("/notice")

public class NoticeServlet extends HttpServlet
{
	 private static final long serialVersionUID = 1L;
	    private JakartaServletWebApplication application;
	    private TemplateEngine templateEngine;
       
	@Override
		public void init() throws ServletException {
		super.init();
        application = JakartaServletWebApplication.buildApplication(getServletContext());
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/Templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
		}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		var out = res.getWriter();
        
        
  	    final IWebExchange webExchange = this.application.buildExchange(req, res);
  	    final WebContext ctx = new WebContext(webExchange);
  	    templateEngine.process("Notice", ctx, out);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final IWebExchange webExchange = this.application.buildExchange(req, res);
  	    final WebContext ctx = new WebContext(webExchange);
        String name = req.getParameter("name");
        String temp = req.getParameter("number");
        int number = Integer.parseInt(temp);
        String content = req.getParameter("content");
//
        Connection cnx = null;
   	 	PreparedStatement stmt = null;
   	 try {

   		 String insert ="insert into noticeboard (name, number, content) values(?,?,?)";
   		 cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/Notice","saisidharthak","root");
   		 System.out.println("hello");
   		 stmt= cnx.prepareStatement(insert);
   		 stmt.setString(1, name);
		 stmt.setInt(2, number);
		 stmt.setString(3, content);
   		 stmt.executeUpdate();
   		 stmt.close();
		 cnx.close();
   	}
 	
   	 catch(Exception e) {
   		 e.printStackTrace();
   	 }
		
		doGet(req, res);
	}

}
