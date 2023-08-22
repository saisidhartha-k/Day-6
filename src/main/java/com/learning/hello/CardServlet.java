package com.learning.hello;

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
        player1 = new Player();
        player2 = new Player();
        application = JakartaServletWebApplication.buildApplication(getServletContext());
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/Templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String reading = "hello";
        
        String player1Name = req.getParameter("player1Name");
        int player1Bet = req.getParameter("player1Bet") != null ? Integer.parseInt(req.getParameter("player1Bet")) : 0;
        String player1Card = req.getParameter("player1Card");
//
        String player2Name = req.getParameter("player2Name");
        int player2Bet = req.getParameter("player2Bet") != null ? Integer.parseInt(req.getParameter("player2Bet")) : 0;
        String player2Card = req.getParameter("player2Card");
//        
   
        player1.Player(player1Name, player1Bet, player1Card, false);
        player2.Player(player2Name, player2Bet, player2Card, true);

        ca.play(ca, player1, player2); 
//        
        var out = res.getWriter();
	    final IWebExchange webExchange = 
	    this.application.buildExchange(req, res);
	    final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("reading", player1.getMoney());
	    ctx.setVariable("reading1", player2.getMoney());

	    templateEngine.process("Card", ctx, out);
    }
}
