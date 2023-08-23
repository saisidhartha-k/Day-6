package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import Controller.CardContoller;
import Controller.Player;

@WebServlet("/form")
public class CardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JakartaServletWebApplication application;
    private TemplateEngine templateEngine;
    private CardContoller ca;
    private Player player1;
    private Player player2;

    @Override
    public void init() throws ServletException {
        super.init();
        ca = new CardContoller();
        application = JakartaServletWebApplication.buildApplication(getServletContext());
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/Templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	var out = res.getWriter();
        
  	    final IWebExchange webExchange = this.application.buildExchange(req, res);
  	    final WebContext ctx = new WebContext(webExchange);
    	String reading = "hello";
        String player1Name = req.getParameter("player1Name");
        int player1Bet = req.getParameter("player1Bet") != null ? Integer.parseInt(req.getParameter("player1Bet")) : 0;
        String player1Card = req.getParameter("player1Card");
//
        String player2Name = req.getParameter("player2Name");
        int player2Bet = req.getParameter("player2Bet") != null ? Integer.parseInt(req.getParameter("player2Bet")) : 0;
        String player2Card = req.getParameter("player2Card");
//        
        
        if (player1 == null && player2 == null) {
        player1 = new Player(player1Name, player1Bet, player1Card, false);
        player2 = new Player(player2Name, player2Bet, player2Card, true);
        }
        
        if("Deal".equals(req.getParameter("Deal")) )
        {
        	System.out.println("inside deal");
        	ArrayList <String> usedValues = ca.play(ca, player1, player2); 
        	System.out.println(usedValues);
            ctx.setVariable("in", usedValues.get(0));
            ctx.setVariable("out", usedValues.get(1));

        }
     if(player1.getWinner() == true || player2.getWinner() == true)
     {
    	 ctx.setVariable("winnerName", player1.getWinner()==true? player1.getName():player2.getName());
    	 ctx.setVariable("reading2",  player1.getWinner()==true? player1.getMoney():player2.getMoney());
     }
 	

 	    
 	   templateEngine.process("Card", ctx, out);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	

//          
        var out = res.getWriter();
        
        
  	    final IWebExchange webExchange = this.application.buildExchange(req, res);
  	    final WebContext ctx = new WebContext(webExchange);
 	    ctx.setVariable("reading1", 0);
 	    ctx.setVariable("reading2", 0);
 	    ctx.setVariable("in", 0);
	    ctx.setVariable("out", 0);

  	    templateEngine.process("Card", ctx, out);
    }
}
