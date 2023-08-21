package com.learning.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import Controller.HighLowController;
import Controller.OdometerControllerpackage;

/**
 * Servlet implementation class OdometerServlet
 */
@WebServlet("/odo")

public class OdometerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
		private OdometerControllerpackage odom;
		  private JakartaServletWebApplication application;
		  private TemplateEngine templateEngine;

		  
		  @Override
		  public void init(ServletConfig config) throws ServletException {
		    super.init(config);
		    odom = new OdometerControllerpackage(5);
		    
		    application = JakartaServletWebApplication.buildApplication(getServletContext());
		    final WebApplicationTemplateResolver templateResolver = 
		        new WebApplicationTemplateResolver(application);
		    templateResolver.setTemplateMode(TemplateMode.HTML);
		    templateResolver.setPrefix("/WEB-INF/Templates/");
		    templateResolver.setSuffix(".html");
		    templateEngine = new TemplateEngine();
		    templateEngine.setTemplateResolver(templateResolver);
		  }
		  
		  
		  
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			String button = req.getParameter("button");
			
			if (button == null) {
	            odom.reset();
	        } else if (button.equals("prev")) {
	            odom.decrementReading();
	        } else if (button.equals("next")) {
	            odom.increment();
	        }
			    var out = resp.getWriter();
			    final IWebExchange webExchange = 
			    this.application.buildExchange(req, resp);
			    final WebContext ctx = new WebContext(webExchange);
			    ctx.setVariable("reading", odom.getReading());
			    templateEngine.process("odometer", ctx, out);
			
		}
//		  @Override
//		  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		    final IWebExchange webExchange = this.application.buildExchange(req, resp);
//		    final WebContext ctx = new WebContext(webExchange);
//		    templateEngine.process("HighLow", ctx, resp.getWriter());
//		  }

	}


