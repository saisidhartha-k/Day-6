package com.learning.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.Out;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import Controller.HighLowController;

/**
 * Servlet implementation class HighLowServlet
 */
@WebServlet("/hilo")
public class HighLowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HighLowController hlc;
	  private JakartaServletWebApplication application;
	  private TemplateEngine templateEngine;

	  
	  @Override
	  public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    hlc = new HighLowController();
	    hlc.reset();
	    application = JakartaServletWebApplication.buildApplication(getServletContext());
	    final WebApplicationTemplateResolver templateResolver = 
	        new WebApplicationTemplateResolver(application);
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    templateResolver.setPrefix("/WEB-INF/Templates/");
	    templateResolver.setSuffix(".html");
	    templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	  }
	  
	  
	  
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 hlc.setGuess(Integer.parseInt(req.getParameter("guess")));
		    var out = resp.getWriter();
		    final IWebExchange webExchange = 
		        this.application.buildExchange(req, resp);
		    final WebContext ctx = new WebContext(webExchange);
		    ctx.setVariable("feedback", hlc.feedback());
		    templateEngine.process("HighLow", ctx, out);
		    if (hlc.judge() == 0)
		      hlc.reset();
	}
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    final IWebExchange webExchange = this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    templateEngine.process("HighLow", ctx, resp.getWriter());
	  }

}
