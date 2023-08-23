package Controller;

public class Player {
    String name;
    Boolean win = false;
    int bet = 0;
    String card ;
    Boolean in = false;
    int winMoney = 0;
    String gotCard;

    
    public Player(String name, int bet, String card, boolean in)
    {
        this.name= name;
        this.bet= bet;
        this.card = card;
        this.in = in;
    }
    
    public String getGotCard()
    {
    	return this.gotCard;
    }
    public void winner(Player loser)
    {
        winMoney = this.bet + loser.bet;
        loser.winMoney=0;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public int getMoney()
    {
    	return this.winMoney;
    }
    public String getCard()
    {
    	return this.card;
    }
    public boolean getWinner()
    {
    	return this.win;
    }
    
    
}
